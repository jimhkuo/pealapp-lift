package code.comet

import net.liftweb._
import http._
import net.liftweb.http.js.jquery.JqJE._
import net.liftweb.http.js.JsCmds._
import scala.xml.Text
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}
import net.liftweb.common.Loggable
import scala.collection.JavaConversions._
import z3.scala.{Z3AST, Z3Config, Z3Context}
import code.comet.util._
import peal.synthesis.{PolicySet, Condition}
import scala.Predef._
import net.liftweb.http.js.jquery.JqJE.JqId
import code.comet.util.Message
import code.comet.util.SaveFile
import code.comet.util.Result
import z3.ModelGetter
import scala.sys.process._
import net.liftweb.util.Props
import java.io.File
import net.liftweb.http.js.jquery.JqJE.JqId
import code.comet.util.Message
import scala.Some
import code.comet.util.Result
import code.comet.util.SaveFile

class PealCometActor extends CometActor with Loggable {
  val resultBuilder = new StringBuilder
  val processLogger = ProcessLogger(
    (o: String) => resultBuilder.append(o + "\n"),
    (e: String) => resultBuilder.append(e + "\n")
  )
  val defaultInput = "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\ncond1 = pSet1 <= 0.5\ncond2 = 0.6 < pSet2\n"
  var inputPolicies = defaultInput
  var majorityVotingCount = 10

  def render = {
    this ! Init

    <form class="lift:form.ajax">
      <div>
        <h3>Input policies:</h3>
        <div>
          {SHtml.ajaxTextarea(inputPolicies, s => {
          inputPolicies = s
          _Noop
        }, "id" -> "policies", "cols" -> "30", "rows" -> "20")}
        </div>
        <div>
          {SHtml.ajaxButton("Reset to sample policies", () => {
          this ! Reset
          _Noop
        }) ++
          SHtml.ajaxButton("Clear policies", () => {
            this ! Clear
            _Noop
          })}{SHtml.ajaxButton("Majority Voting", () => {
          this ! MajorityVoting
          _Noop
        })}{SHtml.ajaxText(majorityVotingCount.toString, s => {
          majorityVotingCount = s.toInt
          _Noop
        }, "id" -> "n", "size" -> "10")}
        </div>
        <div>
          {SHtml.ajaxButton("Synthesise (and show results)", () => {
          this ! Display
          _Noop
        }) ++ SHtml.ajaxButton("Synthesise (and download)", () => {
          this ! Prepare
          _Noop
        }) ++ SHtml.ajaxButton("Synthesis and call z3", () => {
          this ! SynthesisAndCallZ3
          _Noop
        })}
        </div>
        <div>
          {SHtml.ajaxButton("Analysis1 (!cond)", () => {
          this ! Analysis1
          _Noop
        }) ++ SHtml.ajaxButton("Analysis2 (cond)", () => {
          this ! Analysis2
          _Noop
        })}
        </div>
        <br/>
        <div>
          <h3>Result:</h3>
          <div id="result"></div>
        </div>
      </div>
    </form>
  }

  override def lowPriority = {
    case Init =>
    case Analysis1 =>
      val (constsMap, conds, pSets) = onCompute(inputPolicies)
      onAnalysis1(constsMap, conds, pSets)
    case SynthesisAndCallZ3 =>
      val (constsMap,  conds, pSets) = onCompute(inputPolicies)
      onCallZ3ViaCommandLine(constsMap, conds,  pSets)
    case Analysis2 =>
      val (constsMap,  conds, pSets) = onCompute(inputPolicies)
      onAnalysis2(constsMap, conds, pSets)
    case Display =>
      val (constsMap, conds,  pSets) = onCompute(inputPolicies)
      onDisplay(constsMap, conds,  pSets)
    case Download =>
      val (constsMap, conds,  pSets) = onCompute(inputPolicies)
      onDownload(constsMap, conds,  pSets)
    case Prepare =>
      partialUpdate(JqId("result") ~> JqHtml(Text("Synthesising... Please wait...")))
      this ! Download
    case SaveFile(result, lapseTime) =>
      Z3SMTData.set(result)
      this ! Result(<p>Output prepared, lapse time:
        <span style="color:red;font-weight: bold;">
          {"%.2f".format(lapseTime.toDouble / 1000000)}
        </span>
        ms, please click
        <a href="download">here</a>
        to download the file</p>)
    case Result(output) => partialUpdate(JqId("result") ~> JqHtml(output))
    case Message(message) => partialUpdate(JqId("result") ~> JqHtml(Text(message)))
    case Clear =>
      this ! Message("")
      partialUpdate(JqId("policies") ~> JqVal(""))
    case MajorityVoting =>
      this ! Message("")
      inputPolicies = "b1 = + (" +
        (for (i <- 0 until majorityVotingCount) yield "(q" + i + " " + "%.3f".format(1.0 / majorityVotingCount) + ")").mkString("") +
        " ) default 0\ncond = 0.5 < pSet\npSet = b1"
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Reset =>
      this ! Message("")
      inputPolicies = defaultInput
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
  }

  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  private def onCompute(input: String): (Map[String, Z3AST], Map[String, Condition], Map[String, PolicySet]) = {
    val pealProgrmParser = getParser(input)
    val z3 = MyZ3Context.is

    try {
      pealProgrmParser.program()
      val predicateNames: Seq[String] = pealProgrmParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct
      val constsMap = predicateNames.toSeq.distinct.map(t => (t, z3.mkBoolConst(t))).toMap
      (constsMap, pealProgrmParser.conds.toMap, pealProgrmParser.pSets.toMap)
    } catch {
      case e2: NullPointerException => dealWithIt(e2)
      case e1: Throwable => dealWithIt(e1)
    }
    //    finally {
    //      z3.delete()
    //    }
  }

  private def onDisplay(constsMap: Map[String, Z3AST], conds: Map[String, Condition], pSets: Map[String, PolicySet]) {
    val declarations = for (name <- constsMap.keys) yield <p>
      {"(declare-const " + name + " Bool)"}
    </p>
    val declarations1 = for (name <- conds.keys) yield <p>
      {"(declare-const " + name + " Bool)"}
    </p>

    val sortedKeys = conds.keys.toSeq.sortWith(_ < _)
    val asserts = for (cond <- sortedKeys) yield {<p>
      {"(assert (= " + cond + " " + conds(cond).synthesis(MyZ3Context.is, constsMap) + "))"}
    </p>}

    //TODO add analyses here

    val result = <p>
      {declarations}
      {declarations1}
      {asserts}
  </p>
    this ! Result(result)
  }

  private def onAnalysis1(constsMap: Map[String, Z3AST], conds: Map[String, Condition], pSets: Map[String, PolicySet]) {
    val sortedKeys = conds.keys.toSeq.sortWith(_ < _)
    val solver = MyZ3Context.is.mkSolver()
    val results = for (name <- sortedKeys) yield {
      solver.reset()
      val cond = MyZ3Context.is.mkBoolConst(name)
      solver.assertCnstr(MyZ3Context.is.mkEq(cond, conds(name).synthesis(MyZ3Context, constsMap)))
      solver.assertCnstr(MyZ3Context.is.mkNot(cond))
      val (sol, model) = ModelGetter.get(solver)

      val result = sol match {
        case Some(x) if x && model.toString().trim == name + " -> false" => <p>!{name} is {sol.get} and model is empty<br/>So {name} is always false<pre>{model}</pre><br/></p>
        case Some(x) if x => <p>!{name} is {sol.get}<br/>So {name} is NOT always true<pre>{model}</pre><br/></p>
        case Some(x) if !x => <p>!{name} is {sol.get}<br/>So {name} is always true<pre>{model}</pre><br/></p>
        case None => <p>Nothing is returned by Z3</p>
      }
      model.delete

      result
    }
    this ! Result(results)
  }

  private def onAnalysis2(constsMap: Map[String, Z3AST], conds: Map[String, Condition], pSets: Map[String, PolicySet]) {
    val sortedKeys = conds.keys.toSeq.sortWith(_ < _)
    val solver = MyZ3Context.is.mkSolver()
    val results = for (name <- sortedKeys) yield {
      solver.reset()
      val cond = MyZ3Context.is.mkBoolConst(name)
      solver.assertCnstr(MyZ3Context.is.mkEq(cond, conds(name).synthesis(MyZ3Context, constsMap)))
      val (sol, model) = ModelGetter.get(solver)

      val result = sol match {
        case Some(x) => <p>{name} is {sol.get}, model is:<pre>{model}</pre><br/></p>
        case None => <p>Nothing is returned by Z3</p>
      }
      model.delete

      result
    }
    this ! Result(results)
  }

  private def onDownload(constsMap: Map[String, Z3AST], conds: Map[String, Condition], pSets: Map[String, PolicySet]) {
    val declarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val declarations1 = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"
    val sortedKeys = conds.keys.toSeq.sortWith(_ < _)
    val start = System.nanoTime()
    val body = for (cond <- sortedKeys) yield {
      "(push)\n(assert (= " + cond + " " + conds(cond).synthesis(MyZ3Context.is, constsMap) + "))\n" +
        "(assert " + cond + ")\n(check-sat)\n(get-model)\n(pop)\n"
    }
    val lapseTime = System.nanoTime() - start
    val z3SMTInput = declarations.mkString("") +declarations1.mkString("") + body.mkString("")
    this ! SaveFile(z3SMTInput, lapseTime)
  }

  private def onCallZ3ViaCommandLine(constsMap: Map[String, Z3AST], conds: Map[String, Condition], pSets: Map[String, PolicySet]) {
    val declarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val declarations1 = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"

    val sortedKeys = conds.keys.toSeq.sortWith(_ < _)
    //TODO replace with analyses
    val body = for (cond <- sortedKeys) yield {
      "(push)\n(assert (= " + cond + " " + conds(cond).synthesis(MyZ3Context.is, constsMap) + "))\n" +
        "(assert " + cond + ")\n(check-sat)\n(get-model)\n(pop)\n"
    }

    val z3SMTInput = declarations.mkString("") +declarations1.mkString("") + body.mkString("")
    val tmp = File.createTempFile("z3file", "")
    (Seq("echo", z3SMTInput) #> tmp).!!
    println("tmpfile: " + tmp.getAbsolutePath)
    resultBuilder.clear()
    val returnCode = Process(Seq("bash", "-c", "z3 -nw -smt2 " + tmp.getAbsolutePath), None, "PATH" -> Props.get("z3.location").get) ! processLogger
    println(resultBuilder.toString())
    tmp.delete()
    this ! Result(<pre>{z3SMTInput}</pre> <pre>Z3 Output:<br/>{resultBuilder.toString()}</pre>)
  }

  private def dealWithIt(e: Throwable) = {
    println("error: " + e.getMessage)
    this ! Message("error: " + e.getMessage)
    throw e
  }
}







