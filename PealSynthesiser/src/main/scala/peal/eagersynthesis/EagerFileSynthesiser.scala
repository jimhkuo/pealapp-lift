package peal.eagersynthesis


object EagerFileSynthesiser extends App {

  val inputFileName: String = args(0)
  println(generate())

  def generate(): String = {
    val input = scala.io.Source.fromFile(inputFileName).mkString
    val start = System.nanoTime()
    val output = new EagerSynthesiser().generate(input)
    val lapseTime = System.nanoTime() - start

    lapseTime.toString + "\n" + output
  }
}
