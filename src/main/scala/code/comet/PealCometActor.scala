package code.comet

import net.liftweb._
import http._
import net.liftweb.http.js.jquery.JqJE._
import net.liftweb.http.js.JsCmds._
import scala.xml.Text
import net.liftweb.common.Loggable
import scala.collection.JavaConversions._
import peal.synthesis.{ExtendedSynthesiser, NewSynthesiser, LazySynthesiser, Condition}
import scala.Predef._
import peal.synthesis.analysis._
import code.lib._
import peal.model.{RandomScoreModelGenerator, MajorityVotingGenerator, ConstantScoreModelGenerator}
import peal.domain.{PealFalse, PealBottom, PealTrue, PolicySet}
import peal.domain.z3.{Sat, PealAst, Term}
import peal.antlr.util.ParserHelper
import peal.z3.Z3Caller
import peal.explicit.ExplicitOutputVerifier
import peal.extended.ExtendedOutputVerifier
import net.liftweb.http.js.jquery.JqJE.JqId
import code.lib.Message
import code.lib.Result
import code.lib.SaveFile
import code.lib.Failed

class PealCometActor extends CometActor with Loggable {

  val inputForNewSynthesis = "POLICIES\n" +
    "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 0.4\n" +
    "b2 = max ((q4 0.1) (q5 0.5) (q6 0.8)) default 0.9\n" +
    "b3 = + ((q7 0.1) (q8 0.3) (q9 0.6)) default 0\n" +
    "b4 = * ((q10 0.3) (q11 0.6) (q12 0.7)) default 1\n" +
    "POLICY_SETS\n" +
    "pSet1 = max(b1, b2)\n" +
    "pSet2 = min(b3, b4)\n" +
    "CONDITIONS\n" +
    "cond1 = pSet1 <= pSet2\n" +
    "cond2 = pSet2 < pSet1\n" +
    "ANALYSES\n" +
    "name1 = always_true? cond1\n" +
    "name2 = always_true? cond2\n"

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
    "DOMAIN_SPECIFICS\n" +
    "(declare-const a Real)\n" +
    "(declare-const b Real)\n" +
    "(assert (= q1 (< a (+ b 1))))\n" +
    "ANALYSES\n" +
    "name1 = always_true? cond1\n" +
    "name2 = equivalent? cond1 cond2\n" +
    "name3 = different? cond1 cond2\n" +
    "name4 = implies? cond4 cond3\n" +
    "name5 = always_false? cond2\n" +
    "name6 = satisfiable? cond4\n"

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
  var z3RawOutput = ""
  var extendedSynthesis = ""
  var constsMap: Map[String, PealAst] = Map()
  var analyses: Map[String, AnalysisGenerator] = Map()
  var majorityVotingCount = 10
  var randomModelParam = "5, 5, 4, 3, 2, 7, 0.5, 0.1"
  var randomModelWithRangeParam = "2, 4, 3, 2, 1, 6, 0.5, 0.1"
  var randomModelParamWithDomain = "2, 3, 1, 1, 1, 9, 0.5, 0.1"

  def render = {
    this ! Init
    <div class="col-lg-12">
      <form class="lift:form.ajax">
        <div class="row">
          <div class="col-sm-12">
          <h3>1. Enter policies, policy sets, conditions, and analyses in the text area below:</h3>
          <h6>Or click on one of the blue buttons to generate a valid input</h6>
          <div>
            {SHtml.ajaxButton("Constant-score sample", () => {this ! Reset; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
            {SHtml.ajaxButton("Non-constant score sample", () => {this ! ResetNonConstant; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
            {SHtml.ajaxButton("Majority-voting sample, n =", () => {this ! MajorityVoting; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
            {SHtml.ajaxText(majorityVotingCount.toString, s => {majorityVotingCount = s.toInt; _Noop}, "id" -> "n", "size" -> "10")}
          </div>
          <div>
            {SHtml.ajaxButton("Random sample without domain specifics: n, m_min, m_max, m_+, m_*, p, th, delta", () => {this ! Generate; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;") }
            {SHtml.ajaxText(randomModelParam, s => {randomModelParam = s; _Noop}, "id" -> "r", "size" -> "30")}
          </div>
          <div>
            {SHtml.ajaxButton("Random sample with domain specifics: n, m_min, m_max, m_+, m_*, p, th, delta", () => {this ! GenerateDomainSpecifics; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
            {SHtml.ajaxText(randomModelParamWithDomain, s => {randomModelParamWithDomain = s; _Noop}, "id" -> "r", "size" -> "30")}
          </div>
          <div>
            {SHtml.ajaxButton("Random sample with ranges: n, m_min, m_max, m_+, m_*, p, th, delta", () => {this ! GenerateWithRange; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
            {SHtml.ajaxText(randomModelWithRangeParam, s => {randomModelWithRangeParam = s; _Noop}, "id" -> "r", "size" -> "30")}
          </div>
          <div>
            {SHtml.ajaxButton("Clear text area", () => {this ! Clear; _Noop}, "class" -> "btn btn-warning btn-sm", "style" -> "margin:2px;")}
          </div>
          </div>
        </div>
        <div class="row" style="margin-top: 5px">
          <div class="col-sm-7">
            {SHtml.ajaxTextarea(inputPolicies, s => {inputPolicies = s; _Noop}, "id" -> "policies", "class" -> "form-control", "rows" -> "20")}
          </div>
          <div class="col-sm-5">
            <h4>2. Choose a synthesis method. Then click on one of the green synthesiser buttons:</h4>
            <ul class="nav nav-tabs">
              <li class="active"><a href="#explicit" data-toggle="tab">Explicit Synthesis</a></li>
              <li><a href="#symbolic" data-toggle="tab">Symbolic Synthesis</a></li>
              <li><a href="#extended" data-toggle="tab">Extended Synthesis</a></li>
              <li><a href="#new" data-toggle="tab">New Synthesis</a></li>
            </ul>
          </div>
          <div class="tab-content">
            <div class="tab-pane active" id="explicit">
              <div class="col-sm-5">
                {SHtml.ajaxButton("Display results of all analyses in pretty printed form and perform verification", () => {this ! SynthesisAndCallZ3QuietAnalysis; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
                {SHtml.ajaxButton("Generate, show, and run Z3 code, display results in pretty-printed and raw form", () => {this ! SynthesisAndCallZ3; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
                {SHtml.ajaxButton("Generate and show Z3 code", () => {this ! Display; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
                {SHtml.ajaxButton("Generate Z3 code and a link to it below", () => {this ! Prepare; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
              </div>
            </div>
            <div class="tab-pane" id="symbolic">
              <div class="col-sm-5">
                {SHtml.ajaxButton("Generate, show, and run Z3 code, display results in raw Z3 form", () => {this ! LazySynthesisAndCallZ3; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
                {SHtml.ajaxButton("Generate and show Z3 code", () => {this ! DisplayLazy; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
                {SHtml.ajaxButton("Generate Z3 code and a link to it below", () => {this ! PrepareLazy; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
              </div>
            </div>
            <div class="tab-pane" id="extended">
              <div class="col-sm-5">
                {SHtml.ajaxButton("Generate, show, and run Z3 code, display results in raw Z3 form", () => {this ! ExtendedSynthesisAndCallZ3; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
                {SHtml.ajaxButton("Certify Z3 results", () => {this ! CertifyExtendedResults; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
              </div>
            </div>
            <div class="tab-pane" id="new">
              <div class="col-sm-5">
                {SHtml.ajaxButton("Generate valid sample for this synthesis", () => {this ! ResetNewDefault; _Noop}, "class" -> "btn btn-primary btn-sm", "style" -> "margin:2px;")}
                {SHtml.ajaxButton("Generate, show, and run Z3 code, display results in raw Z3 form", () => {this ! NewSynthesisAndCallZ3; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
                {SHtml.ajaxButton("Generate and show Z3 code", () => {this ! DisplayNew; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
                {SHtml.ajaxButton("Generate Z3 code and a link to it below", () => {this ! PrepareNew; _Noop}, "class" -> "btn btn-success btn-sm", "style" -> "margin:2px;")}
              </div>
            </div>
          </div>
        </div>
        <div class="row" style="margin-top: 2px">
          <div class="col-sm-12" id="result"></div>
        </div>
      </form>
    </div>
  }

  override def lowPriority = {
    case Init =>
    case DisplayNew => this ! Result(<pre>{performNewSynthesis(inputPolicies)}</pre>)
    case NewSynthesisAndCallZ3 => onCallZ3(performNewSynthesis(inputPolicies))
    case PrepareNew =>
      partialUpdate(JqId("result") ~> JqHtml(Text("Synthesising... Please wait...")))
      this ! DownloadNew
    case DownloadNew =>
      val start = System.nanoTime()
      val newSynthesis = performNewSynthesis(inputPolicies)
      val lapseTime = System.nanoTime() - start
      this ! SaveFile(newSynthesis, lapseTime)
    case DisplayLazy => this ! Result(<pre>{performLazySynthesis(inputPolicies)}</pre>)
    case LazySynthesisAndCallZ3 => onCallZ3(performLazySynthesis(inputPolicies))
    case ExtendedSynthesisAndCallZ3 =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      this.constsMap = constsMap
      this.analyses = analyses
      extendedSynthesis = performExtendedSynthesis(inputPolicies)
      onCallZ3(extendedSynthesis)
    case CertifyExtendedResults => certifyResults(extendedSynthesis)
    case PrepareLazy =>
      partialUpdate(JqId("result") ~> JqHtml(Text("Synthesising... Please wait...")))
      this ! DownloadLazy
    case DownloadLazy =>
      val start = System.nanoTime()
      val lazySynthesis = performLazySynthesis(inputPolicies)
      val lapseTime = System.nanoTime() - start
      this ! SaveFile(lazySynthesis, lapseTime)
    case SynthesisAndCallZ3 =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      onCallEagerZ3(true, constsMap, conds,  pSets, analyses, domainSpecific)
    case SynthesisAndCallZ3QuietAnalysis =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      onCallEagerZ3(false, constsMap, conds,  pSets, analyses, domainSpecific)
    case Display =>
      val (constsMap, conds,  pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      onDisplay(constsMap, conds,  pSets, analyses, domainSpecific)
    case Prepare =>
      partialUpdate(JqId("result") ~> JqHtml(Text("Synthesising... Please wait...")))
      this ! Download
    case Download =>
      val (constsMap, conds,  pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      onDownload(constsMap, conds,  pSets, analyses, domainSpecific)
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
      clearIntermediateResults
      partialUpdate(JqId("policies") ~> JqVal(""))
    case MajorityVoting =>
      this ! Message("")
      inputPolicies = MajorityVotingGenerator.generateForCount(majorityVotingCount)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Reset =>
      this ! Message("")
      inputPolicies = defaultInput
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case ResetNewDefault =>
      this ! Message("")
      inputPolicies = inputForNewSynthesis
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case ResetNonConstant =>
      this ! Message("")
      inputPolicies = nonConstantDefaultInput
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Generate =>
      this ! Message("")
      inputPolicies = ConstantScoreModelGenerator.generate(randomModelParam.split(Array(' ', ',')).filterNot(_ == ""):_*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case GenerateWithRange =>
      this ! Message("")
      clearIntermediateResults
      inputPolicies = RandomScoreModelGenerator.generate(randomModelWithRangeParam.split(Array(' ', ',')).filterNot(_ == ""):_*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case GenerateDomainSpecifics =>
      this ! Message("")
      inputPolicies = ConstantScoreModelGenerator.generate(true, randomModelParamWithDomain.split(Array(' ', ',')).filterNot(_ == ""):_*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
  }

  private def clearIntermediateResults {
    z3RawOutput = ""
    extendedSynthesis = ""
    constsMap = Map()
    analyses = Map()
  }

  private def performLazySynthesis(policies: String): String = {
    try {
      new LazySynthesiser(policies).generate()
    } catch {
      case e: Exception =>
        e.printStackTrace()
        dealWithIt(e)
    }
  }

  private def performExtendedSynthesis(policies: String): String = {
    try {
      new ExtendedSynthesiser(policies).generate()
    } catch {
      case e: Exception =>
        e.printStackTrace()
        dealWithIt(e)
    }
  }

  private def performNewSynthesis(policies: String): String = {
    try {
      new NewSynthesiser(policies).generate()
    } catch {
      case e: Exception =>
        e.printStackTrace()
        dealWithIt(e)
    }
  }

  private def parseInput(input: String): (Map[String, PealAst], Map[String, Condition], Map[String, PolicySet], Map[String, AnalysisGenerator], Array[String]) = {
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
    val declarations = for (name <- constsMap.keys) yield "(declare-const " + name + " Bool)\n"
    val condDeclarations = for (name <- conds.keys) yield "(declare-const " + name + " Bool)\n"
    val sortedConds = conds.keys.toSeq.sortWith(_ < _)
    val conditions = for (cond <- sortedConds) yield "(assert (= " + cond + " " + conds(cond).synthesis(constsMap) + "))\n"
    val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)
    val generatedAnalyses = for (analysis <- sortedAnalyses) yield analyses(analysis).z3SMTInput + "\n"
    val result = <pre>{declarations.mkString("") +
      condDeclarations.mkString("") +
      conditions.mkString("") +
      domainSpecifics.map(s => s).mkString("") +
      generatedAnalyses.mkString("")}
    </pre>
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

    val z3RawOutput = Z3Caller.call(z3SMTInput)
    try {
      val z3OutputParser = ParserHelper.getZ3OutputParser(z3RawOutput)
      val z3OutputModels = z3OutputParser.results().toMap

      val verificationResults = for (analysis <- sortedAnalyses if (z3OutputModels(analysis).satResult == Sat)) yield {
        val verifiedModel = new ExplicitOutputVerifier(inputPolicies).verifyModel(z3RawOutput, analysis)
        val result = verifiedModel._1 match {
          case PealTrue => "succeeded"
          case PealFalse => "failed"
          case PealBottom => "was inconclusive"
        }
        "Independent verification of correctness of scenario [" + analysis + "] " + result + ", modified predicates are " + verifiedModel._2 + "\n"
      }

      val analysedResults = Z3OutputAnalyser.execute(analyses, z3OutputModels, constsMap)

      verbose match {
        case true => this ! Result(<pre>{z3SMTInput}</pre> <pre>Analysed results:<br/>{analysedResults}</pre><pre>{verificationResults.mkString("")}</pre><pre>Z3 Raw Output:<br/>{z3RawOutput}</pre>)
        case false => this ! Result(<pre>Analysed results:<br/>{analysedResults}</pre><pre>{verificationResults.mkString("")}</pre>)
      }
    } catch {
      case e: Exception =>
        verbose match {
          case true => this ! Result(<pre>{z3SMTInput}</pre> <pre>Z3 Raw Output:<br/>{z3RawOutput}</pre>)
          case false => this ! Result(<pre>Result analysis failed, returned model contains unexpected string:<br/>{z3RawOutput}</pre><pre>{e.getMessage}</pre>)
        }
    }
  }

  private def onCallZ3(z3SMTInput : String) {
    try {
      z3RawOutput = Z3Caller.call(z3SMTInput)

      this ! Result(<pre>{z3SMTInput}</pre><pre>Z3 Raw Output:<br/>{z3RawOutput}</pre>)
    } catch {
      case e: Exception =>  dealWithIt(e)
    }
  }

  private def certifyResults(z3SMTInput: String) {
    try {
      if (z3RawOutput == "") throw new RuntimeException("Need to generate Z3 results first")
      val z3OutputParser = ParserHelper.getZ3OutputParser(z3RawOutput)
      val z3OutputModels = z3OutputParser.results().toMap
      val sortedAnalyses = analyses.keys.toSeq.sortWith(_ < _)

      val verificationResults = for (analysis <- sortedAnalyses if (z3OutputModels(analysis).satResult == Sat)) yield {
        val verifiedModel = new ExtendedOutputVerifier(inputPolicies).verifyModel(z3RawOutput, analysis)
        val result = verifiedModel._1 match {
          case PealTrue => "succeeded"
          case PealFalse => "failed"
          case PealBottom => "was inconclusive"
        }
        "Independent verification of correctness of scenario [" + analysis + "] " + result + ", modified predicates are " + verifiedModel._2 + "\n"
      }

      val analysedResults = Z3OutputAnalyser.execute(analyses, z3OutputModels, constsMap)

      this ! Result(<pre>{verificationResults.mkString("")}</pre><pre>Analysed results:<br/>{analysedResults}</pre><pre>{z3SMTInput}</pre> <pre>Z3 Raw Output:<br/>{z3RawOutput}</pre>)
    } catch {
      case e: Exception =>  dealWithIt(e)
    }
  }

  private def dealWithIt(e: Throwable) = {
    this ! Failed(e.getMessage)
    throw e
  }
}







