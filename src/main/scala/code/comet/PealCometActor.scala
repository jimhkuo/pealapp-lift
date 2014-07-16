package code.comet

import code.lib._
import net.liftweb.http._
import net.liftweb.http.js.JsCmds._
import net.liftweb.http.js.jquery.JqJE._
import peal.domain.PolicySet
import peal.domain.z3.PealAst
import peal.helper.PealCometHelper
import peal.model.{ConstantScoreModelGenerator, MajorityVotingGenerator, RandomScoreModelGenerator}
import peal.synthesis._
import peal.synthesis.analysis._
import peal.util.ConsoleLogger
import peal.verifier.OutputVerifier
import peal.z3.Z3Caller

import scala.util.{Try, Failure, Success}
import scala.xml.Text

class PealCometActor extends MainBody with CometListener {

  def registerWith = CometServer

  def render: RenderOut = {
    this ! Init
    generateHTMLContents
  }

  override def lowPriority: PartialFunction[Any, Unit] = handleBasicActions orElse handleSynthesisActions

  def handleBasicActions: PartialFunction[Any, Unit] = {
    case Init =>
    case Message(message) => partialUpdate(JqId("result") ~> JqHtml(Text(message)))
    case Failed(message) => partialUpdate(JqId("result") ~> JqHtml(<h3>Error:</h3> ++ Text(message)))
    case Result(output) => partialUpdate(JqId("result") ~> JqHtml(<h3>3. Generated output:</h3> ++ output))
    case ResetConstantInput =>
      this ! Message("")
      inputPolicies = socialNetworkExample
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case ResetNonConstantInput =>
      this ! Message("")
      inputPolicies = carRentalExample
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case ResetMajorityVotingInput =>
      this ! Message("")
      inputPolicies = MajorityVotingGenerator.generateForCount(majorityVotingCount)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case GenerateConstantScoreInput =>
      this ! Message("")
      inputPolicies = ConstantScoreModelGenerator.generate(randomModelParam.split(Array(' ', ',')).filterNot(_ == ""): _*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case GenerateConstantScoreWithDomainSpecifics =>
      this ! Message("")
      inputPolicies = ConstantScoreModelGenerator.generate(true, randomModelParamWithDomain.split(Array(' ', ',')).filterNot(_ == ""): _*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case GenerateInputWithRange =>
      this ! Message("")
      inputPolicies = RandomScoreModelGenerator.generate(randomModelWithRangeParam.split(Array(' ', ',')).filterNot(_ == ""): _*)
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Clear =>
      this ! Message("")
      inputPolicies = ""
      partialUpdate(JqId("policies") ~> JqVal(""))
    case UploadFile(id, s) =>
      val myId = for (sess <- S.session) yield sess.uniqueId
      ConsoleLogger.log("id received: " + id)
      if (myId.toList(0) == id && s != "") {
        inputPolicies = s
        partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
      }
  }

  def handleSynthesisActions: PartialFunction[Any, Unit] = {
    case SynthesisAndCallZ3QuietAnalysis =>
      performSynthesisAndCertify(PealCometHelper.performExplicitSynthesis, false)
    case RunAndCertifyExplicitResults =>
      performSynthesisAndCertify(PealCometHelper.performExplicitSynthesis, true)
    case ExtendedSynthesisAndCallZ3 =>
      PealCometHelper.performExtendedSynthesis(inputPolicies) match {
        case Success(v) => onCallZ3(v)
        case Failure(e) => dealWithIt(e)
      }
    case SynthesisExtendedAndCallZ3QuietAnalysis =>
      performSynthesisAndCertify(PealCometHelper.performExtendedSynthesis, false)
    case RunAndCertifyExtendedResults =>
      performSynthesisAndCertify(PealCometHelper.performExtendedSynthesis, true)
    case ExplicitSynthesisAndCallZ3 =>
      PealCometHelper.performExplicitSynthesis(inputPolicies) match {
        case Success(v) => onCallZ3(v)
        case Failure(e) => dealWithIt(e)
      }
  }

  private def onCallZ3(z3SMTInput: String) {
    try {
      val z3RawOutput = Z3Caller.call(z3SMTInput)

      this ! Result(<pre>Generated Z3 code:<br/><br/>{z3SMTInput}</pre><pre>Z3 Raw Output:<br/>{z3RawOutput}</pre>)
    } catch {
      case e: Exception => dealWithIt(e)
    }
  }

  private def performSynthesisAndCertify(synthesiser: String => Try[String], isVerbose: Boolean) {
    val result = for {
      parsedInput <- PealCometHelper.parseInput(inputPolicies)
      synthesisResult <- synthesiser(inputPolicies)
    } yield {
      (parsedInput, synthesisResult)
    }

    result match {
      case Failure(e) => dealWithIt(e)
      case Success(v) => v match {
        case (parsedInput: (Map[String, PealAst], Map[String, Condition], Map[String, PolicySet], Map[String, AnalysisGenerator], Array[String]), synthesisResult) =>
          certifyResults(isVerbose, parsedInput._1, parsedInput._4, synthesisResult)
      }
    }
  }

  private def certifyResults(verbose: Boolean, constsMap: Map[String, PealAst], analyses: Map[String, AnalysisGenerator], z3SMTInput: String) {
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
        dealWithIt(e)
    }
  }

  private def dealWithIt(e: Throwable) = {
    //        e.printStackTrace()
    this ! Failed(e.getMessage)
    throw e
  }
}







