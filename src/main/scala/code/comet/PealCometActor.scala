package code.comet

import code.lib._
import net.liftweb.http._
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.jquery.JqJE._
import peal.antlr.util.ParserHelper
import peal.domain.PolicySet
import peal.domain.z3.{PealAst, Term}
import peal.model.{ConstantScoreModelGenerator, MajorityVotingGenerator, RandomScoreModelGenerator}
import peal.synthesis._
import peal.synthesis.analysis._
import peal.util.ConsoleLogger
import peal.verifier.OutputVerifier
import peal.z3.Z3Caller

import scala.Predef._
import scala.collection.JavaConversions._
import scala.xml.Text

class PealCometActor extends MainBody with CometListener {

  def registerWith = CometServer


  def render: RenderOut = {
    val myId = for (sess <- S.session) yield sess.uniqueId
    println("render " + myId.toList(0))

    this ! Init
    
    generateContents
  }

  override def lowPriority = {
    case Init =>
    case Message(message) => partialUpdate(JqId("result") ~> JqHtml(Text(message)))
    case Failed(message) => partialUpdate(JqId("result") ~> JqHtml(<h3>Error:</h3> ++ Text(message)))
    case Result(output) => partialUpdate(JqId("result") ~> JqHtml(<h3>3. Generated output:</h3> ++ output))
    case Reset =>
      this ! Message("")
      inputPolicies = socialNetworkExample
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case ResetNonConstant =>
      this ! Message("")
      inputPolicies = carRentalExample
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case MajorityVoting =>
      this ! Message("")
      inputPolicies = MajorityVotingGenerator.generateForCount(majorityVotingCount)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Generate =>
      this ! Message("")
      inputPolicies = ConstantScoreModelGenerator.generate(randomModelParam.split(Array(' ', ',')).filterNot(_ == ""):_*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case GenerateDomainSpecifics =>
      this ! Message("")
      inputPolicies = ConstantScoreModelGenerator.generate(true, randomModelParamWithDomain.split(Array(' ', ',')).filterNot(_ == ""):_*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case GenerateWithRange =>
      this ! Message("")
      clearIntermediateResults
      inputPolicies = RandomScoreModelGenerator.generate(randomModelWithRangeParam.split(Array(' ', ',')).filterNot(_ == ""):_*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Clear =>
      this ! Message("")
      inputPolicies = ""
      clearIntermediateResults
      partialUpdate(JqId("policies") ~> JqVal(""))
    case SynthesisAndCallZ3QuietAnalysis =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      this.constsMap = constsMap
      this.analyses = analyses
      certifyResults(false, performExplicitSynthesis(inputPolicies))
    case RunAndCertifyExplicitResults =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      this.constsMap = constsMap
      this.analyses = analyses
      certifyResults(true, performExplicitSynthesis(inputPolicies))
    case ExplicitSynthesisAndCallZ3 =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      this.constsMap = constsMap
      this.analyses = analyses
      explicitSynthesis = performExplicitSynthesis(inputPolicies)
      onCallZ3(explicitSynthesis)
    case SynthesisExtendedAndCallZ3QuietAnalysis =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      this.constsMap = constsMap
      this.analyses = analyses
      certifyResults(false, performExtendedSynthesis(inputPolicies))
    case RunAndCertifyExtendedResults =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      this.constsMap = constsMap
      this.analyses = analyses
      certifyResults(true, performExtendedSynthesis(inputPolicies))
    case ExtendedSynthesisAndCallZ3 =>
      val (constsMap,  conds, pSets, analyses, domainSpecific) = parseInput(inputPolicies)
      this.constsMap = constsMap
      this.analyses = analyses
      extendedSynthesis = performExtendedSynthesis(inputPolicies)
      onCallZ3(extendedSynthesis)
    case UploadFile(id, s) =>
      val myId = for (sess <- S.session) yield sess.uniqueId

      ConsoleLogger.log("id received: " + id)
      ConsoleLogger.log1("UploadFile(s) received, s = " + s)
      if (myId.toList(0) == id && s != "") {
        inputPolicies = s
        partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
      }
  }

  override protected def localShutdown(): Unit = {
    val myId = for (sess <- S.session) yield sess.uniqueId
    println("shutting down " + myId.toList(0))
    super.localShutdown()
  }

  private def clearIntermediateResults() {
    z3RawOutput = ""
    extendedSynthesis = ""
    explicitSynthesis = ""
    constsMap = Map()
    analyses = Map()
  }

  private def performLazySynthesis(policies: String): String = {
    try {
      new LazySynthesiser(policies).generate()
    } catch {
      case e: Exception =>
        dealWithIt(e)
    }
  }

  private def performExtendedSynthesis(policies: String): String = {
    try {
      new ExtendedSynthesiser(policies).generate()
    } catch {
      case e: Exception =>
        dealWithIt(e)
    }
  }

  private def performNewSynthesis(policies: String): String = {
    try {
      new NewSynthesiser(policies).generate()
    } catch {
      case e: Exception =>
        dealWithIt(e)
    }
  }

  private def performExplicitSynthesis(policies: String): String = {
    try {
      new EagerSynthesiser(policies).generate()
    } catch {
      case e: Exception =>
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

  private def onCallZ3(z3SMTInput : String) {
    try {
      z3RawOutput = Z3Caller.call(z3SMTInput)

      this ! Result(<pre>Generated Z3 code:<br/><br/>{z3SMTInput}</pre><pre>Z3 Raw Output:<br/>{z3RawOutput}</pre>)
    } catch {
      case e: Exception =>  dealWithIt(e)
    }
  }

  private def certifyResults(verbose: Boolean, z3SMTInput: String) {
    try {
      val z3RawOutput = Z3Caller.call(z3SMTInput)
      implicit val ov = new OutputVerifier(inputPolicies)
      val analysedResults = Z3OutputAnalyser.execute(analyses, constsMap, inputPolicies, z3RawOutput)
      verbose match {
        case true => this ! Result(<pre>Generated Z3 code:<br/><br/>{z3SMTInput}</pre><span>{analysedResults}</span><pre>Z3 Raw Output:<br/>{z3RawOutput}</pre>)
        case false =>this ! Result(<span>{analysedResults}</span>)
      }
    } catch {
        case e: Exception =>
         e.printStackTrace()
         verbose match {
           case true => this ! Result(<pre>Generated Z3 code:<br/><br/>{z3SMTInput}</pre> <pre>Z3 Raw Output:<br/>{z3RawOutput}</pre>)
           case false => this ! Result(<pre>Result analysis failed, returned model contains unexpected string:<br/>{z3RawOutput}</pre><pre>{e.getMessage}</pre>)
        }
    }
  }

  private def dealWithIt(e: Throwable) = {
    //        e.printStackTrace()
    this ! Failed(e.getMessage)
    throw e
  }
}







