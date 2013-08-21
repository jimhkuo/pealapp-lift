package peal.runner.actor

import akka.actor.Actor
import scala.sys.process._
import java.io.{FileWriter, File}
import scala.collection.mutable.ListBuffer
import util.FileUtil


class Z3CallerActor(z3Path: String, memoryLimit: Long) extends Actor {
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
//    println(tmp.getAbsolutePath)
    //TODO restricting bash memory usage to 8G, doesn't work
      Process(Seq("bash", "-c", "ulimit", "-v" , memoryLimit.toString)) #&& Process(Seq("z3", "-nw", "-smt2", tmp.getAbsolutePath), None, "PATH" -> z3Path) ! processLogger
//      Process(Seq("limit", "vmemoryuse" , memoryLimit.toString)) #&& Process(Seq(z3Path + "/z3", "-nw", "-smt2", tmp.getAbsolutePath)) ! processLogger
      tmp.delete()

      sender ! resultList.mkString("\n")
  }
}
