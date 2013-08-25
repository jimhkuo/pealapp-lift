package peal.runner.actor

import akka.actor.Actor
import scala.sys.process._
import java.io.File
import scala.collection.mutable.ListBuffer
import util.FileUtil
import peal.runner.ResultAnalyser


class Z3CallerActor(memoryLimit: Long) extends Actor {
  val resultList = ListBuffer[String]()
  val processLogger = ProcessLogger(
    (o: String) => resultList.append(o + "\n"),
    (e: String) => resultList.append(e + "\n")
  )

  def receive = {
    case input: String =>
      val tmp = File.createTempFile("z3file", "")
      val execTmp = File.createTempFile("callz3", "")
      execTmp.setExecutable(true)
      FileUtil.writeToFile(tmp.getAbsolutePath, input)
      val script = "#!/bin/sh\nulimit -v " + memoryLimit + "\nz3 -nw -smt2 " + tmp.getAbsolutePath + "\n"
      FileUtil.writeToFile(execTmp.getAbsolutePath, script)
      val returnCode = Seq(execTmp.getAbsolutePath) ! processLogger
      val resultsMap = ResultAnalyser.execute(resultList.mkString("\n"))
      tmp.delete()
      execTmp.delete()

      if (resultsMap.isEmpty) {
        println(resultList.mkString("\n"))
        println(returnCode)
        throw new RuntimeException("Killed due to memory restriction")
      }

      sender ! resultsMap
  }
}
