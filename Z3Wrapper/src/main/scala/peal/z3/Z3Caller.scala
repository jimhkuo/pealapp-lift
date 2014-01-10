package peal.z3

import scala.sys.process._
import java.io.File
import scala.collection.mutable.ListBuffer
import util.FileUtil

object Z3Caller {
  def processLogger(buffer: ListBuffer[String]) = ProcessLogger(
    (o: String) => buffer.append(o + "\n"),
    (e: String) => buffer.append(e + "\n")
  )

  def call (z3SMTInput:String ) ={
    val buffer = ListBuffer[String]()
    val tmp = File.createTempFile("z3file", "")
    FileUtil.writeToFile(tmp.getAbsolutePath, z3SMTInput)
    Process(Seq("bash", "-c", "z3 -nw -smt2 " + tmp.getAbsolutePath)) ! processLogger(buffer)
    tmp.delete()
    buffer.mkString("")
  }
}
