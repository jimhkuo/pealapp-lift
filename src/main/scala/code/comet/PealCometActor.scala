package code.comet

//merge test

import _root_.util.FileUtil
import net.liftweb._
import http._
import net.liftweb.http.js.jquery.JqJE._
import net.liftweb.http.js.JsCmds._
import scala.xml.Text
import net.liftweb.common.Loggable
import scala.collection.JavaConversions._
import peal.synthesis.Condition
import scala.Predef._
import scala.sys.process._
import java.io.File
import peal.synthesis.analysis._
import scala.collection.mutable.ListBuffer
import code.lib._
import net.liftweb.http.js.jquery.JqJE.JqId
import peal.model.{MajorityVotingGenerator, RandomModelGenerator}
import peal.domain.PolicySet
import peal.lazysynthesis.LazySynthesiser
import peal.domain.z3.{PealAst, Term}
import peal.antlr.util.ParserHelper


class PealCometActor extends CometActor with Loggable {
  val resultList = ListBuffer[String]()
  val processLogger = ProcessLogger(
    (o: String) => resultList.append(o + "\n"),
    (e: String) => resultList.append(e + "\n")
  )

  val defaultInput = "POLICIES\n" +
    "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
    "b2 = + ((q4 0.1) (q5 0.2) (q6 0.6)) default 0\n" +
    "POLICY_SETS\n" +
    "pSet1 = max(b1, b2)\n" +
    "pSet2 = min(b1, b2)\n" +
    "CONDITIONS\n" +
    "cond1 = pSet1 <= 0.5\n" +
    "cond2 = 0.6 < pSet2\n" +
    "cond3 = cond1 && cond2\n" +
    "cond4 = cond1 || cond2\n" +
    "cond5 = !cond4\n" +
    "cond6 = true\n" +
    "cond7 = false\n" +
    "DOMAIN_SPECIFICS\n" +
    "(declare-const a Real)\n" +
    "(declare-const b Real)\n" +
    "(assert (= q1 (< a (+ b 1))))\n" +
    "ANALYSES\n" +
    "name1 = always_true? cond1\n" +
    "name2 = equivalent? cond1 cond2\n" +
    "name3 = different? cond1 cond2\n" +
    "name4 = implies? cond3 cond4\n" +
    "name5 = always_true? cond3\n" +
    "name6 = always_true? cond4\n" +
    "name7 = always_true? cond5\n" +
    "name8 = always_true? cond6\n" +
    "name9 = always_false? cond7\n"

  val nonConstantDefaultInput = "POLICIES\n" +
    "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.8*z\n" +
    "b2 = + ((q4 0.1*x) (q5 y*0.2) (q6 y)) default 0\n" +
    "POLICY_SETS\n" +
    "pSet1 = max(b1, b2)\n" +
    "pSet2 = min(b1, b2)\n" +
    "CONDITIONS\n" +
    "cond1 = pSet1 <= 0.5\n" +
    "cond2 = 0.6 < pSet2\n" +
    "cond3 = 0.5 < pSet2\n" +
    "cond4 = 0.4 < pSet2\n" +
    "DOMAIN_SPECIFICS\n" +
    "(declare-const a Real)\n" +
    "(declare-const b Real)\n" +
    "(assert (= q1 (< a (+ b 1))))\n" +
    "ANALYSES\n" +
    "name1 = always_true? cond1\n" +
    "name2 = always_false? cond1\n" +
    "name3 = satisfiable? cond2\n" +
    "name4 = equivalent? cond1 cond2\n" +
    "name5 = different? cond1 cond2\n" +
    "name6 = implies? cond1 cond2\n"
  var inputPolicies = defaultInput
  var majorityVotingCount = 10
  var randomModelParam = "5, 5, 4, 3, 2, 7, 0.5, 0.1"
  var randomModelParamWithDomain = "2, 3, 1, 1, 1, 9, 0.5, 0.1"

  def render = {
    this ! Init

    <form class="lift:form.ajax">
      <div>
        <h3>1. Enter policies, policy sets, conditions, and analyses in the textarea below:</h3>
        <h6>Or click on one of the blue buttons to generate a valid input</h6>
        <div class="form-group">
        <div>
          {SHtml.ajaxButton("Constant-score sample", () => {this ! Reset; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;") ++
          SHtml.ajaxButton("Non-constant score sample", () => {
            this ! ResetNonConstant
            _Noop
          }, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}{SHtml.ajaxButton("Majority-voting sample, n =", () => {
          this ! MajorityVoting
          _Noop
        }, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}{SHtml.ajaxText(majorityVotingCount.toString, s => {
          majorityVotingCount = s.toInt
          _Noop
        }, "id" -> "n", "size" -> "10")}
        </div>
        <div>
          {SHtml.ajaxButton("Random sample without domain specifics: n, m_min, m_max, m_+, m_*, p, th, delta", () => {
          this ! Generate
          _Noop
        }, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;") }{SHtml.ajaxText(randomModelParam, s => {
          randomModelParam = s
          _Noop
        }, "id" -> "r", "size" -> "30")}
        </div>
        <div>
          {SHtml.ajaxButton("Random sample with domain specifics: n, m_min, m_max, m_+, m_*, p, th, delta", () => {
          this ! GenerateDomainSpecifics
          _Noop
        }, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;") }{SHtml.ajaxText(randomModelParamWithDomain, s => {
          randomModelParamWithDomain = s
          _Noop
        }, "id" -> "r", "size" -> "30")}
        </div>
        <div>{
          SHtml.ajaxButton("Clear input panel", () => {
            this ! Clear
            _Noop
          }, "class" -> "btn btn-warning btn-sm", "style" -> "margin:2px;")}
        </div>
        </div>
        <div class="form-group">
          <div class="col-lg-6">
          {SHtml.ajaxTextarea(inputPolicies, s => {
          inputPolicies = s
          _Noop
        }, "id" -> "policies", "class" -> "form-control", "cols" -> "30", "rows" -> "20")}
        </div>
        <div class="col-lg-4">
          <h4>2. Then click on one of the green synthesiser buttons:</h4>
          <ul class="nav nav-tabs" id="myTab">
            <li class="active"><a href="#explicit" data-toggle="tab">Explicit Synthesis</a></li>
            <li><a href="#symbolic" data-toggle="tab">Symbolic Synthesis</a></li>
          </ul>
        </div>

          <div class="tab-content">
            <div class="tab-pane active" id="explicit">
              <div class="col-lg-3">
              {SHtml.ajaxButton("Display results of all analyses in pretty printed form", () => {
                this ! SynthesisAndCallZ3Quiet
                _Noop
              }, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;") ++
                SHtml.ajaxButton("Generate, show, and run Z3 code, display results in pretty-printed and raw form", () => {
                  this ! SynthesisAndCallZ3
                  _Noop
                }, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")++
                SHtml.ajaxButton("Generate and show Z3 code", () => {
                this ! Display
                _Noop
              }, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;") ++
              SHtml.ajaxButton("Generate and download Z3 code", () => {
                this ! Prepare
                _Noop
              }, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;") }
            </div>
            </div>
            <div class="tab-pane" id="symbolic">
              <div class="col-lg-3">
                {SHtml.ajaxButton("Generate and display Z3 code", () => {
                this ! LazyDisplay
                _Noop
              }, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")++
                SHtml.ajaxButton("Generate, display, and run Z3 code, display results of all analyses in raw Z3 form", () => {
                this ! LazySynthesisAndCallZ3
                _Noop
              }, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
              </div>
            </div>
          </div>

        </div>

        <div class="form-group col-lg-10">
          <hr/>
          <div id="result"></div>
        </div>
      </div>
    </form>
  }

  override def lowPriority = {
    case Init =>
    case SynthesisAndCallZ3 =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = onCompute(inputPolicies)
      onCallEagerZ3(true, constsMap, conds,  pSets, analyses, domainSpecific)
    case SynthesisAndCallZ3Quiet =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = onCompute(inputPolicies)
      onCallEagerZ3(false, constsMap, conds,  pSets, analyses, domainSpecific)
    case LazySynthesisAndCallZ3 =>
      val input = new LazySynthesiser(inputPolicies).generate()
      onCallLazyZ3(input)
    case LazyDisplay =>
      this ! Result(<pre>{new LazySynthesiser(inputPolicies).generate()}</pre>)
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
    case Result(output) => partialUpdate(JqId("result") ~> JqHtml(<h3>3. Generated output:</h3> ++ output))
    case Message(message) => partialUpdate(JqId("result") ~> JqHtml(Text(message)))
    case Failed(message) => partialUpdate(JqId("result") ~> JqHtml(<h3>Error:</h3> ++ Text(message)))
    case Clear =>
      this ! Message("")
      inputPolicies = ""
      partialUpdate(JqId("policies") ~> JqVal(""))
    case MajorityVoting =>
      this ! Message("")
      inputPolicies = MajorityVotingGenerator.generateForCount(majorityVotingCount)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Reset =>
      this ! Message("")
      inputPolicies = defaultInput
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case ResetNonConstant =>
      this ! Message("")
      inputPolicies = nonConstantDefaultInput
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Generate =>
      this ! Message("")
      inputPolicies = RandomModelGenerator.generate(randomModelParam.split(Array(' ', ',')).filterNot(_ == ""):_*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case GenerateDomainSpecifics =>
      this ! Message("")
      inputPolicies = RandomModelGenerator.generate(true, randomModelParamWithDomain.split(Array(' ', ',')).filterNot(_ == ""):_*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
  }

  private def onCompute(input: String): (Map[String, PealAst], Map[String, Condition], Map[String, PolicySet], Map[String, AnalysisGenerator], Array[String]) = {
    val pealProgramParser = ParserHelper.getPealParser(input)

    try {
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
      {"(assert (= " + cond + " " + conds(cond).synthesis(constsMap) + "))"}
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
    val body = for (cond <- sortedKeys) yield {"(assert (= " + cond + " " + conds(cond).synthesis(constsMap) + "))\n"}
    val lapseTime = System.nanoTime() - start
    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {analyses(analysis).z3SMTInput}
    val z3SMTInput = declarations.mkString("") +declarations1.mkString("") + body.mkString("") + domainSpecifics.mkString("", "\n","\n") + generatedAnalyses.mkString("")
    this ! SaveFile(z3SMTInput, lapseTime)
  }

  private def onCallEagerZ3(verbose: Boolean, constsMap: Map[String, PealAst], conds: Map[String, Condition], pSets: Map[String, PolicySet], analyses: Map[String, AnalysisGenerator], domainSpecifics: Array[String]) {
    val declarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val declarations1 = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"
    val sortedKeys = conds.keys.toSeq.sortWith(_ < _)
    val body = for (cond <- sortedKeys) yield {"(assert (= " + cond + " " + conds(cond).synthesis(constsMap) + "))\n"}
    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield {"(echo \"Result of analysis [" + analyses(analysis).analysisName + "]:\")\n" + analyses(analysis).z3SMTInput}

    val z3SMTInput = declarations.mkString("") +declarations1.mkString("") + body.mkString("") + domainSpecifics.mkString("", "\n","\n") + generatedAnalyses.mkString("")
    val tmp = File.createTempFile("z3file", "")
    FileUtil.writeToFile(tmp.getAbsolutePath, z3SMTInput)
    resultList.clear()
    Process(Seq("bash", "-c", "z3 -nw -smt2 " + tmp.getAbsolutePath)) ! processLogger
    tmp.delete()

    try {
      val z3OutputParser = ParserHelper.getZ3OutputParser(resultList.mkString(""))
      val z3Models = z3OutputParser.results().toMap
      val analysedResults = Z3OutputAnalyser.execute(analyses, z3Models, constsMap)
      verbose match {
        case true => this ! Result(<pre>{z3SMTInput}</pre> <pre>Analysed results:<br/>{analysedResults}<br/><br/>Z3 Raw Output:<br/>{resultList.mkString("")}</pre>)
        case false => this ! Result(<pre>Analysed results:<br/>{analysedResults}</pre>)
      }
    } catch {
      case e: Exception =>
        verbose match {
          case true => this ! Result(<pre>{z3SMTInput}</pre> <pre>Z3 Raw Output:<br/>{resultList.mkString("")}</pre>)
          case false => this ! Result(<pre>Result analysis failed, returned model contains unexpected string:<br/>{resultList.mkString("")}</pre>)
        }

      //        dealWithIt(e)
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
    this ! Failed(e.getMessage)
    throw e
  }
}







