package code.lib

import scala.xml.NodeSeq

case class Message(output: String)

case class Result(output: NodeSeq)

case class SaveFile(result: String, lapseTime: Long)

case object Init

case object Clear
case object Generate

case object Download

case object Prepare

case object Reset

case object MajorityVoting

case object Display

case object Analysis1

case object SynthesisAndCallZ3

case object Analysis2

