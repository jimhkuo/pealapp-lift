package code.comet

import _root_.util.FileUtil
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
import peal.synthesis.Condition
import scala.Predef._
import z3.ModelGetter
import scala.sys.process._
import java.io.File
import peal.synthesis.analysis._
import scala.collection.mutable.ListBuffer
import code.lib._
import net.liftweb.http.js.jquery.JqJE.JqId
import code.lib.Message
import scala.Some
import code.lib.Result
import code.lib.SaveFile
import peal.model.RandomModelGenerator
import peal.domain.PolicySet
import peal.lazysynthesis.LazySynthesiser
import peal.domain.z3.wrapper.{Term, PealAst}


class PealCometActor extends CometActor with Loggable {
  val resultList = ListBuffer[String]()
  val processLogger = ProcessLogger(
    (o: String) => resultList.append(o + "\n"),
    (e: String) => resultList.append(e + "\n")
  )

  val defaultInput = "POLICIES\n" +
    "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
    "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
    "POLICY_SETS\n" +
    "pSet1 = max(b1, b2)\n" +
    "pSet2 = min(b1, b2)\n" +
    "CONDITIONS\n" +
    "cond1 = pSet1 <= 0.5\n" +
    "cond2 = 0.6 < pSet2\n" +
    "cond3 = 0.5 < pSet2\n" +
    "cond4 = 0.4 < pSet2\n" +
    "DOMAIN_SPECIFICS\n" +
    "(declare-const x Real)\n" +
    "(declare-const y Real)\n" +
    "(assert (= q1 (< x (+ y 1))))\n" +
    "ANALYSES\n" +
    "name1 = always_true? cond1\n" +
    "name2 = always_false? cond1\n" +
    "name3 = satisfiable? cond2\n" +
    "name4 = equivalent? cond1 cond2\n" +
    "name5 = different? cond1 cond2\n"
  var inputPolicies = defaultInput
  var majorityVotingCount = 10
  var randomModelParam = "5, 5, 4, 3, 2, 7, 0.5, 0.1"

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
          {SHtml.ajaxButton("Generate random model", () => {
          this ! Generate
          _Noop
        }) }{SHtml.ajaxText(randomModelParam, s => {
          randomModelParam = s
          _Noop
        }, "id" -> "r", "size" -> "30")}
        </div>
        <div>
          {SHtml.ajaxButton("EXPLICIT synthesise", () => {
          this ! Display
          _Noop
        }) ++ SHtml.ajaxButton("EXPLICIT synthesise and call z3", () => {
          this ! SynthesisAndCallZ3
          _Noop
        })}
          <span>||</span>
          {SHtml.ajaxButton("EXPLICIT synthesise (and download)", () => {
          this ! Prepare
          _Noop
        }) }
        </div>
        <div>
          {SHtml.ajaxButton("SYMBOLIC synthesise", () => {
          this ! LazyDisplay
          _Noop
        })++ SHtml.ajaxButton("SYMBOLIC synthesis and call z3", () => {
          this ! LazySynthesisAndCallZ3
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
    case SynthesisAndCallZ3 =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = onCompute(inputPolicies)
      onCallZ3ViaCommandLine(constsMap, conds,  pSets, analyses, domainSpecific)
    case LazySynthesisAndCallZ3 =>
      val input = new LazySynthesiser(MyZ3Context.get, inputPolicies).generate()
      onCallLazyZ3(input)
    case LazyDisplay =>
      this ! Result(<pre>{new LazySynthesiser(MyZ3Context.get, inputPolicies).generate()}</pre>)
    case Display =>
      val (constsMap, conds,  pSets, analyses, domainSpecific) = onCompute(inputPolicies)
      onDisplay(constsMap, conds,  pSets, analyses, domainSpecific)
    case Download =>
      val (constsMap, conds,  pSets, analyses, domainSpecific) = onCompute(inputPolicies)
      onDownload(constsMap, conds,  pSets, analyses, domainSpecific)
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
        (for (i <- 0 until majorityVotingCount) yield "(q" + i + " " + BigDecimal.valueOf(1.0 / majorityVotingCount) + ")").mkString("") +
        " ) default 0\npSet = b1\ncond = 0.5 < pSet\nANALYSES\nanalysis = always_true? cond\n"
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Reset =>
      this ! Message("")
      inputPolicies = defaultInput
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Generate =>
      this ! Message("")
      inputPolicies = RandomModelGenerator.generate(randomModelParam.split(Array(' ', ',')).filterNot(_ == ""):_*)
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

  private def onCompute(input: String): (Map[String, PealAst], Map[String, Condition], Map[String, PolicySet], Map[String, AnalysisGenerator], Array[String]) = {
    val pealProgramParser = getPealProgramParser(input)

    try {
      val z3 = MyZ3Context.get

      pealProgramParser.program()
      val predicateNames: Seq[String] = pealProgramParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct
      val constsMap = predicateNames.toSeq.distinct.map(t => (t, Term(t))).toMap
      val domainSpecifics = input.split("\n").dropWhile(!_.startsWith("DOMAIN_SPECIFICS")).takeWhile(!_.startsWith("ANALYSES")).drop(1)

      (constsMap, pealProgramParser.conds.toMap, pealProgramParser.pSets.toMap, pealProgramParser.analyses.toMap, domainSpecifics)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        dealWithIt(e)
      case e2: NullPointerException =>
        e2.printStackTrace()
        dealWithIt(e2)
      case e1: Throwable =>
        e1.printStackTrace()
        dealWithIt(e1)
    }
  }

  private def onDisplay(constsMap: Map[String, PealAst], conds: Map[String, Condition], pSets: Map[String, PolicySet], analyses: Map[String, AnalysisGenerator], domainSpecifics: Array[String]) {
    val declarations = for (name <- constsMap.keys) yield <p>
      {"(declare-const " + name + " Bool)"}
    </p>
    val declarations1 = for (name <- conds.keys) yield <p>
      {"(declare-const " + name + " Bool)"}
    </p>

    val sortedConds = conds.keys.toSeq.sortWith(_ < _)
    val conditions = for (cond <- sortedConds) yield {<p>
      {"(assert (= " + cond + " " + conds(cond).synthesis(MyZ3Context.get, constsMap) + "))"}
    </p>}

    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {
      <pre>{analyses(analysis).z3SMTInput}</pre>
    }

    val result = <span>
      {declarations}
      {declarations1}
      {conditions}
      {domainSpecifics.map(s => <p>{s}</p>)}
      {generatedAnalyses}
    </span>
    this ! Result(result)
  }

  private def onDownload(constsMap: Map[String, PealAst], conds: Map[String, Condition], pSets: Map[String, PolicySet], analyses: Map[String, AnalysisGenerator], domainSpecifics: Array[String]) {
    val declarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val declarations1 = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"
    val sortedKeys = conds.keys.toSeq.sortWith(_ < _)
    val start = System.nanoTime()
    val body = for (cond <- sortedKeys) yield {"(assert (= " + cond + " " + conds(cond).synthesis(MyZ3Context.get, constsMap) + "))\n"}
    val lapseTime = System.nanoTime() - start
    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {analyses(analysis).z3SMTInput}
    val z3SMTInput = declarations.mkString("") +declarations1.mkString("") + body.mkString("") + domainSpecifics.mkString("", "\n","\n") + generatedAnalyses.mkString("")
    this ! SaveFile(z3SMTInput, lapseTime)
  }

  private def onCallZ3ViaCommandLine(constsMap: Map[String, PealAst], conds: Map[String, Condition], pSets: Map[String, PolicySet], analyses: Map[String, AnalysisGenerator], domainSpecifics: Array[String]) {
    val declarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val declarations1 = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"
    val sortedKeys = conds.keys.toSeq.sortWith(_ < _)
    val body = for (cond <- sortedKeys) yield {"(assert (= " + cond + " " + conds(cond).synthesis(MyZ3Context.get, constsMap) + "))\n"}
    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {"(echo \"Result of analysis [" + analyses(analysis).analysisName + "]:\")\n" + analyses(analysis).z3SMTInput}

    val z3SMTInput = declarations.mkString("") +declarations1.mkString("") + body.mkString("") + domainSpecifics.mkString("", "\n","\n") + generatedAnalyses.mkString("")
    val tmp = File.createTempFile("z3file", "")
    FileUtil.writeToFile(tmp.getAbsolutePath, z3SMTInput)
    resultList.clear()
    Process(Seq("bash", "-c", "z3 -nw -smt2 " + tmp.getAbsolutePath)) ! processLogger
    tmp.delete()
    try {
      val z3OutputParser = getZ3OutputParser(resultList.mkString(""))
      val z3Models = z3OutputParser.results().toMap
      val analysedResults = Z3OutputAnalyser.execute(analyses, z3Models, constsMap)

      this ! Result(<pre>{z3SMTInput}</pre> <pre>Analysed results:<br/>{analysedResults}<br/><br/>Z3 Raw Output:<br/>{resultList.mkString("")}</pre>)
    } catch {
      case e: Exception =>  dealWithIt(e)
    }
  }

  private def onCallLazyZ3(z3SMTInput : String) {
    val tmp = File.createTempFile("z3file", "")
    FileUtil.writeToFile(tmp.getAbsolutePath, z3SMTInput)
    resultList.clear()
    Process(Seq("bash", "-c", "z3 -nw -smt2 " + tmp.getAbsolutePath)) ! processLogger
    tmp.delete()
    try {
      this ! Result(<pre>{z3SMTInput}</pre><span>Z3 Raw Output:<br/></span><pre>{resultList.mkString("")}</pre>)
    } catch {
      case e: Exception =>  dealWithIt(e)
    }
  }

  private def dealWithIt(e: Throwable) = {
    println("error: " + e.getMessage)
    this ! Message("error: " + e.getMessage)
    throw e
  }
}







