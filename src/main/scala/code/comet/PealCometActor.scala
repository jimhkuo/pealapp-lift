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
    case Message(text) => partialUpdate(JqId("result") ~> JqHtml(Text(text)))
    case Failed(text) => partialUpdate(JqId("result") ~> JqHtml(<h3>Error:</h3> ++ Text(text)))
    case Result(html) => partialUpdate(JqId("result") ~> JqHtml(<h3>3. Generated output:</h3> ++ html))
    case SocialNetworkAccessControl => updatePealInput(socialNetworkExample)
    case CarRentalRisks => updatePealInput(carRentalExample)
    case FireFaultTree => updatePealInput(fireFaultTreeExample)
    case MajorityVoting => updatePealInput(MajorityVotingGenerator.generateForCount(majorityVotingCount))
    case GenerateConstantScore => updatePealInput(ConstantScoreModelGenerator.generate(randomModelParam.split(Array(' ', ',')).filterNot(_ == ""): _*))
    case GenerateConstantScoreWithDomainSpecifics => updatePealInput(ConstantScoreModelGenerator.generate(true, randomModelParamWithDomain.split(Array(' ', ',')).filterNot(_ == ""): _*))
    case GenerateScoresWithRange => updatePealInput(RandomScoreModelGenerator.generate(randomModelWithRangeParam.split(Array(' ', ',')).filterNot(_ == ""): _*))
    case Clear => updatePealInput("")
    case UploadFile(id, fileContent) =>
      val myId = for (liftSession <- S.session) yield liftSession.uniqueId
      ConsoleLogger.log("id received: " + id)
      if (myId.toList(0) == id && fileContent != "") {
        updatePealInput(fileContent)
      }
  }

  def handleSynthesisActions: PartialFunction[Any, Unit] = {
    case SynthesisAndCallZ3QuietAnalysis => performSynthesisAndCertify(PealCometHelper.tryExplicitSynthesis, isVerbose = false)
    case RunAndCertifyExplicitResults => performSynthesisAndCertify(PealCometHelper.tryExplicitSynthesis, isVerbose = true)
    case ExplicitSynthesisAndCallZ3 => performSynthesisOnly(PealCometHelper.tryExplicitSynthesis)
    case SynthesisExtendedAndCallZ3QuietAnalysis => performSynthesisAndCertify(PealCometHelper.tryExtendedSynthesis, isVerbose = false)
    case RunAndCertifyExtendedResults => performSynthesisAndCertify(PealCometHelper.tryExtendedSynthesis, isVerbose = true)
    case ExtendedSynthesisAndCallZ3 => performSynthesisOnly(PealCometHelper.tryExtendedSynthesis)
  }

  private def updatePealInput(input: String) {
    this ! Message("")
    inputPolicies = input
    partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
  }

  private def performSynthesisOnly(synthesiser: String => Try[String]): Unit = synthesiser(inputPolicies) match {
    case Success(v) => callZ3Only(v)
    case Failure(e) => dealWithIt(e)
  }

  private def performSynthesisAndCertify(synthesiser: String => Try[String], isVerbose: Boolean) {
    val result: Try[((Map[String, PealAst], Map[String, Condition], Map[String, PolicySet], Map[String, AnalysisGenerator], Array[String]), String)] = for {
      parsedInput <- PealCometHelper.tryToParsePealInput(inputPolicies)
      synthesisResult <- synthesiser(inputPolicies)
    } yield {
      (parsedInput, synthesisResult)
    }

    result match {
      case Success(v) => callZ3AndCertifyResults(isVerbose, constsMap = v._1._1, analyses = v._1._4, z3SMTInput = v._2)
      case Failure(e) => dealWithIt(e)
    }
  }

  private def callZ3Only(z3SMTInput: String) {
    try {
      val z3RawOutput = Z3Caller.call(z3SMTInput)

      this ! Result(<pre>Generated Z3 code:<br/><br/>{z3SMTInput}</pre><pre>Z3 Raw Output:<br/>{z3RawOutput}</pre>)
    } catch {
      case e: Exception => dealWithIt(e)
    }
  }

  private def callZ3AndCertifyResults(verbose: Boolean, constsMap: Map[String, PealAst], analyses: Map[String, AnalysisGenerator], z3SMTInput: String) {
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
    e.printStackTrace()
    this ! Failed(e.getMessage)
    throw e
  }
}







