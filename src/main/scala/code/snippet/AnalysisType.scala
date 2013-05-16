package code.snippet

import net.liftweb.http.SHtml
import net.liftweb.common.Empty

object AnalysisType {
  val types = List("Specified Cond", "Sensitive Analysis")

  def selectedOption(in: String) {

  }

  def render = SHtml.selectObj[String](types.map(t => (t, t)), Empty, selectedOption _)
}
