package peal.runner

object ResultAnalyser {

  val spec = """Result of analysis \[([-\w. =?]+)\]:""".r
  val status = """([-\w]+)""".r

  def execute(input: String): Map[String, String] = {

    var out = Map[String, String]()

    var n = ""
    input.split("\n").foreach {
      line =>
        line match {
          case spec(name) => n = name
          case status(s) => out += n -> s
          case _ =>
        }
    }

    out
  }

}
