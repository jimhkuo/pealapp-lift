package code.comet.message

import scala.xml.NodeSeq

case class Message(output: String)

case object Init

case object Clear

case object Download

case object Prepare

case object Reset

case object MajorityVoting

case object Compute

case class Result(output: NodeSeq)

case class File(result: String, lapseTime: Long)
