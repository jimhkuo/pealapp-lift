package peal.verify

import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Test
import peal.synthesis.EagerSynthesiser
import java.io.File
import util.FileUtil
import scala.sys.process._
import scala.collection.mutable.ListBuffer


class ExplicitOutputVerifierTest extends ShouldMatchersForJUnit {

  val resultList = ListBuffer[String]()
  val processLogger = ProcessLogger(
    (o: String) => resultList.append(o + "\n"),
    (e: String) => resultList.append(e + "\n")
  )

  @Test
  def testCanVerifySimpleModel() {
    val input = "POLICIES\nb1 = + ((q1 0.1) (q2 0.2) (q3 0.6)) default 0\nPOLICY_SETS\npSet1 = b1\nCONDITIONS\ncond1 = 0.8 < pSet1\nANALYSES\nname1 = always_true? cond1"
    val z3SMTInput = new EagerSynthesiser(input).generate()

    println(z3SMTInput)

    //TODO call z3 here
    val tmp = File.createTempFile("z3file", "")
    FileUtil.writeToFile(tmp.getAbsolutePath, z3SMTInput)
    resultList.clear()
    Process(Seq("bash", "-c", "z3 -nw -smt2 " + tmp.getAbsolutePath)) ! processLogger
    tmp.delete()
    println(resultList.mkString(""))
  }
}
