package peal.runner

object ReturnedModelAnalyser {
  val spec = """Result of analysis \[([-\w. =?]+)\]:""".r

  def execute(input: String): Map[String, String] = {
    var out = Map[String, String]()
    var cachedName = ""
    input.split("\n").foreach {
      line =>
        line match {
          case spec(analysisName) => cachedName = analysisName
          case s if (s.trim == "unsat" || s.trim == "sat") => out += cachedName -> s
          case _ =>
        }
    }

    out
  }
}
