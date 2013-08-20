package peal.runner.actor

import akka.actor.Actor
import scala.sys.process._
import java.io.{FileWriter, File}
import scala.collection.mutable.ListBuffer
import util.FileUtil


class Z3CallerActor(z3Path: String) extends Actor {
  val resultList = ListBuffer[String]()
  val processLogger = ProcessLogger(
    (o: String) => resultList.append(o + "\n"),
    (e: String) => resultList.append(e + "\n")
  )

  def receive = {
    //TODO this is not advisable, google for solution on dealing with huge messages
    case input: String =>
      val tmp = File.createTempFile("z3file", "")
      FileUtil.writeToFile(tmp.getAbsolutePath, input)
      Process(Seq("bash", "-c", "z3 -nw -smt2 " + tmp.getAbsolutePath), None, "PATH" -> z3Path) ! processLogger
      tmp.delete()

      sender ! resultList.mkString("\n")
  }
}
