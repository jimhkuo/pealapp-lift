package peal.runner.actor

import akka.actor.Actor
import scala.sys.process._
import java.io.File
import scala.collection.mutable.ListBuffer
import util.FileUtil
import peal.runner.ReturnedModelAnalyser


class Z3CallerActor(memoryLimit: Long) extends Actor {
  val resultList = ListBuffer[String]()
  val processLogger = ProcessLogger(
    (o: String) => resultList.append(o + "\n"),
    (e: String) => resultList.append(e + "\n")
  )

  def receive = {
    case inputFile: File =>
      val execTmp = File.createTempFile("runZ3-", "")
      execTmp.setExecutable(true)
      val script = "#!/bin/sh\nulimit -v " + memoryLimit + "\nz3 -nw -smt2 " + inputFile.getAbsolutePath + "\n"
      FileUtil.writeToFile(execTmp.getAbsolutePath, script)
      val returnCode = Seq(execTmp.getAbsolutePath) ! processLogger
      val resultsMap = ReturnedModelAnalyser.execute(resultList.mkString("\n"))
      execTmp.delete()

      if (resultsMap.isEmpty) {
        println(resultList.mkString("\n"))
        println(returnCode)
        println(inputFile.getAbsolutePath)
        throw new RuntimeException("Killed due to memory restriction")
      }
      else {
        inputFile.delete()
      }

      sender ! resultsMap
  }
}
