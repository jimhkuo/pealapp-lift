package code.lib

import scala.xml.NodeSeq

case class Message(output: String)

case class Failed(output: String)

case class Result(output: NodeSeq)

case class SaveFile(result: String, lapseTime: Long)

case object Init

case object Clear

case object Generate

case object GenerateDomainSpecifics

case object Download

case object DownloadLazy

case object DownloadNew

case object Prepare

case object PrepareLazy

case object PrepareNew

case object Reset

case object ResetNewDefault

case object ResetNonConstant

case object MajorityVoting

case object Display

case object DisplayLazy

case object DisplayNew

case object Analysis1

case object SynthesisAndCallZ3

case object SynthesisAndCallZ3Quiet

case object LazySynthesisAndCallZ3

case object NewSynthesisAndCallZ3

case object Analysis2

