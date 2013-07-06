package code.comet.util

import scala.xml.NodeSeq

case class Message(output: String)

case class Result(output: NodeSeq)

case class File(result: String, lapseTime: Long)

case object Init

case object Clear

case object Download

case object Prepare

case object Reset

case object MajorityVoting

case object Display

