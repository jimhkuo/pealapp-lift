package code.comet

import net.liftweb._
import http._
import net.liftweb.http.js.jquery.JqJE._
import net.liftweb.http.js.JsCmds._
import scala.xml.Text
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{Z3OutputParser, Z3OutputLexer, PealProgramParser, PealProgramLexer}
import net.liftweb.common.Loggable
import scala.collection.JavaConversions._
import z3.scala.Z3AST
import code.comet.util._
import peal.synthesis.{PolicySet, Condition}
import scala.Predef._
import z3.ModelGetter
import scala.sys.process._
import net.liftweb.util.Props
import java.io.File
import net.liftweb.http.js.jquery.JqJE.JqId
import code.comet.util.Message
import scala.Some
import code.comet.util.Result
import code.comet.util.SaveFile
import peal.synthesis.analysis._
import scala.collection.mutable.ListBuffer
import peal.domain.z3.{Unsat, Model}
import net.liftweb.http.js.jquery.JqJE.JqId
import code.comet.util.Message
import scala.Some
import peal.synthesis.analysis.Satisfiable
import code.comet.util.Result
import code.comet.util.SaveFile
import peal.synthesis.analysis.AlwaysFalse
import peal.synthesis.analysis.AlwaysTrue

class PealCometActor extends CometActor with Loggable {
  val resultList = ListBuffer[String]()
  val processLogger = ProcessLogger(
    (o: String) => resultList.append(o + "\n"),
    (e: String) => resultList.append(e + "\n")
  )
  val defaultInput = "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\nb2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\npSet1 = max(b1, b2)\npSet2 = min(b1, b2)\n" +
    "cond1 = pSet1 <= 0.5\n" +
    "cond2 = 0.6 < pSet2\n" +
    "cond3 = 0.5 < pSet2\n" +
    "cond4 = 0.4 < pSet2\n" +
    "ANALYSES\n" +
    "name1 = always_true? cond1\n" +
    "name2 = always_false? cond1\n" +
    "name3 = satisfiable? cond2\n" +
    "name4 = equivalent? cond1 cond2\n" +
    "name5 = different? cond1 cond2\n"
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
        <div style="display: none;">
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
      val (constsMap, conds, pSets, analyses) = onCompute(inputPolicies)
      onAnalysis1(constsMap, conds, pSets)
    case Analysis2 =>
      val (constsMap,  conds, pSets, analyses) = onCompute(inputPolicies)
      onAnalysis2(constsMap, conds, pSets)
    case SynthesisAndCallZ3 =>
      val (constsMap,  conds, pSets, analyses) = onCompute(inputPolicies)
      onCallZ3ViaCommandLine(constsMap, conds,  pSets, analyses)
    case Display =>
      val (constsMap, conds,  pSets, analyses) = onCompute(inputPolicies)
      onDisplay(constsMap, conds,  pSets, analyses)
    case Download =>
      val (constsMap, conds,  pSets, analyses) = onCompute(inputPolicies)
      onDownload(constsMap, conds,  pSets, analyses)
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
        " ) default 0\npSet = b1\ncond = 0.5 < pSet\n"
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Reset =>
      this ! Message("")
      inputPolicies = defaultInput
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
  }

  private def getZ3OutputParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new Z3OutputLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new Z3OutputParser(tokenStream)
  }

  private def getPealProgramParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  private def onCompute(input: String): (Map[String, Z3AST], Map[String, Condition], Map[String, PolicySet], Map[String, AnalysisGenerator]) = {
    val pealProgrmParser = getPealProgramParser(input)
    val z3 = MyZ3Context.is

    try {
      pealProgrmParser.program()
      val predicateNames: Seq[String] = pealProgrmParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct
      val constsMap = predicateNames.toSeq.distinct.map(t => (t, z3.mkBoolConst(t))).toMap
      (constsMap, pealProgrmParser.conds.toMap, pealProgrmParser.pSets.toMap, pealProgrmParser.analyses.toMap)
    } catch {
      case e2: NullPointerException => dealWithIt(e2)
      case e1: Throwable => dealWithIt(e1)
    }
    //    finally {
    //      z3.delete()
    //    }
  }

  private def onDisplay(constsMap: Map[String, Z3AST], conds: Map[String, Condition], pSets: Map[String, PolicySet], analyses: Map[String, AnalysisGenerator]) {
    val declarations = for (name <- constsMap.keys) yield <p>
      {"(declare-const " + name + " Bool)"}
    </p>
    val declarations1 = for (name <- conds.keys) yield <p>
      {"(declare-const " + name + " Bool)"}
    </p>

    val sortedConds = conds.keys.toSeq.sortWith(_ < _)
    val conditions = for (cond <- sortedConds) yield {<p>
      {"(assert (= " + cond + " " + conds(cond).synthesis(MyZ3Context.is, constsMap) + "))"}
    </p>}

    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {
      <pre>{analyses(analysis).z3SMTInput}</pre>
    }

    val result = <span>
      {declarations}
      {declarations1}
      {conditions}
      {generatedAnalyses}
  </span>
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

  private def onDownload(constsMap: Map[String, Z3AST], conds: Map[String, Condition], pSets: Map[String, PolicySet], analyses: Map[String, AnalysisGenerator]) {
    val declarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val declarations1 = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"
    val sortedKeys = conds.keys.toSeq.sortWith(_ < _)
    val start = System.nanoTime()
    val body = for (cond <- sortedKeys) yield {"(assert (= " + cond + " " + conds(cond).synthesis(MyZ3Context.is, constsMap) + "))\n"}
    val lapseTime = System.nanoTime() - start
    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {analyses(analysis).z3SMTInput}
    val z3SMTInput = declarations.mkString("") +declarations1.mkString("") + body.mkString("") + generatedAnalyses.mkString("")
    this ! SaveFile(z3SMTInput, lapseTime)
  }

  private def onCallZ3ViaCommandLine(constsMap: Map[String, Z3AST], conds: Map[String, Condition], pSets: Map[String, PolicySet], analyses: Map[String, AnalysisGenerator]) {
    val declarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val declarations1 = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"
    val sortedKeys = conds.keys.toSeq.sortWith(_ < _)
    val body = for (cond <- sortedKeys) yield {"(assert (= " + cond + " " + conds(cond).synthesis(MyZ3Context.is, constsMap) + "))\n"}
    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {"(echo \"Result of analysis [" + analyses(analysis).analysisName + "]:\")\n" + analyses(analysis).z3SMTInput}

    val z3SMTInput = declarations.mkString("") +declarations1.mkString("") + body.mkString("") + generatedAnalyses.mkString("")
    val tmp = File.createTempFile("z3file", "")
    (Seq("echo", z3SMTInput) #> tmp).!!
    resultList.clear()
    Process(Seq("bash", "-c", "z3 -nw -smt2 " + tmp.getAbsolutePath), None, "PATH" -> Props.get("z3.location").get) ! processLogger
    tmp.delete()
    val z3Output = getZ3OutputParser(resultList.mkString(""))
    val results = z3Output.results()

    val analysedResults = performAnalysis(analyses, results.toMap, constsMap)

    this ! Result(<pre>{z3SMTInput}</pre> <pre>Z3 Output:<br/>{resultList.mkString("")}<br/>{analysedResults}</pre>)
  }


  private def performAnalysis(analyses: Map[String, AnalysisGenerator], results: Map[String, Model], constsMap: Map[String, Z3AST]) : String = {
    val out = ListBuffer[String]()

    analyses.keys.toSeq.sortWith(_ < _).foreach{
      a =>
        out.append("\nResult of analysis: " + analyses(a).analysisName)
        analyses(a) match {
          case s : AlwaysTrue =>
            if (results(a).satResult == Unsat) {
              out.append(s.cond + " is always true")
            }
            else {
              out.append(s.cond + " is NOT always true")
              out.append("For example, when\n" + getReasons(results(a), constsMap))
            }
          case s: AlwaysFalse =>
            if (results(a).satResult == Unsat) {
              out.append(s.cond + " is always false")
            }
            else {
              out.append(s.cond + " is NOT always false")
              out.append("For example, when\n" + getReasons(results(a), constsMap))
            }
          case s: Satisfiable =>
            if (results(a).satResult == Unsat) {
              out.append(s.cond + " is NOT satisfiable")
            }
            else {
              out.append(s.cond + " is satisfiable")
              out.append("For example, when\n" + getReasons(results(a), constsMap))
            }
          case s: Different =>
            if (results(a).satResult == Unsat) {
              out.append(s.lhs + " and " + s.rhs + " are NOT different")
            }
            else {
              out.append(s.lhs + " and " + s.rhs + " are different")
              out.append("For example, when\n" + getReasons(results(a), constsMap))
            }
          case s: Equivalent =>
            if (results(a).satResult == Unsat) {
              out.append(s.lhs + " and " + s.rhs + " are equivalent")
            }
            else {
              out.append(s.lhs + " and " + s.rhs + " are NOT equivalent")
              out.append("For example, when\n" + getReasons(results(a), constsMap))
            }
        }
    }

    out.mkString("\n")
  }

  private def getReasons(model: Model, constsMap: Map[String, Z3AST]) = {
    val out = for (define <- model.defines if constsMap.contains(define.name)) yield {
      define.name + " is " + define.value
    }

    out.mkString("\n")
  }

  private def dealWithIt(e: Throwable) = {
    println("error: " + e.getMessage)
    this ! Message("error: " + e.getMessage)
    throw e
  }
}







