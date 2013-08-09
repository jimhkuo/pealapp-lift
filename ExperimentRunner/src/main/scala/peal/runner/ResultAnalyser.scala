package peal.runner

object ResultAnalyser {

  val spec = """Result of analysis \[([-\w. =?]+)\]:""".r

  def execute(input: String) : Map[String, String] = {

    input.split("\n").foreach {
      line =>
           line match {
             case spec(name) =>
             case _ =>
           }
    }

    Map()
  }

}
