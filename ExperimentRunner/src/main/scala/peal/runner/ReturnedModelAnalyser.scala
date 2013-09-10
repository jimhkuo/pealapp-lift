package peal.runner

object ReturnedModelAnalyser {
  val spec = """Result of analysis \[([-\w. =?]+)\]:""".r
  val status = """([-\w]+)""".r

  def execute(input: String): Map[String, String] = {
    var out = Map[String, String]()
    var cachedName = ""
    input.split("\n").foreach {
      line =>
        line match {
          case spec(analysisName) => cachedName = analysisName
          case status(statusString) => out += cachedName -> statusString
          case _ =>
        }
    }

    out
  }
}
