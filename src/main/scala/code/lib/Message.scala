package code.lib

import scala.xml.NodeSeq

case class Message(output: String)

case class Failed(output: String)

case class Result(output: NodeSeq)

case class SaveFile(result: String, lapseTime: Long)

case class UploadFile(id: String, content: String)

case object Init

case object Clear

case object GenerateConstantScoreInput

case object GenerateConstantScoreWithDomainSpecifics

case object GenerateInputWithRange

case object Download

case object DownloadLazy

case object DownloadNew

case object Prepare

case object PrepareLazy

case object PrepareNew

case object ResetConstantInput

case object ResetNewDefault

case object ResetNonConstantInput

case object ResetMajorityVotingInput

case object ExplicitSynthesisAndCallZ3

case object DisplayLazy

case object DisplayExtended

case object DisplayNew

case object Analysis1

case object RunAndCertifyExplicitResults

case object SynthesisAndCallZ3QuietAnalysis

case object SynthesisExtendedAndCallZ3QuietAnalysis

case object LazySynthesisAndCallZ3

case object ExtendedSynthesisAndCallZ3

case object RunAndCertifyExtendedResults

case object NewSynthesisAndCallZ3

case object Analysis2

