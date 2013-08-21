package peal.runner.actor

import akka.actor.Actor
import scala.sys.process._
import java.io.{FileWriter, File}
import scala.collection.mutable.ListBuffer
import util.FileUtil


class Z3CallerActor(memoryLimit: Long) extends Actor {
  val resultList = ListBuffer[String]()
  val processLogger = ProcessLogger(
    (o: String) => resultList.append(o + "\n"),
    (e: String) => resultList.append(e + "\n")
  )

  def receive = {
    //TODO this is not advisable, google for solution on dealing with huge messages
    case input: String =>
      val tmp = File.createTempFile("z3file", "")
      val execTmp = File.createTempFile("callz3", "")
      execTmp.setExecutable(true)
      FileUtil.writeToFile(tmp.getAbsolutePath, input)
      val script = "#!/bin/sh\nulimit -v " + memoryLimit + "\nz3 -nw -smt2 " + tmp.getAbsolutePath + "\n"
      FileUtil.writeToFile(execTmp.getAbsolutePath, script)
      //      Process(Seq("bash", "-c", "ulimit", "-v", memoryLimit.toString)) #&& Process(Seq("z3", "-nw", "-smt2", tmp.getAbsolutePath)) ! processLogger
      val returnCode = Seq(execTmp.getAbsolutePath) ! processLogger
      //      println(execTmp.getAbsolutePath + " " + returnCode)

      if (returnCode != 0 && returnCode != 1) {
        throw new RuntimeException("Kill due to memory restriction")
      }

      tmp.delete()
      execTmp.delete()

      sender ! resultList.mkString("\n")
  }
}
