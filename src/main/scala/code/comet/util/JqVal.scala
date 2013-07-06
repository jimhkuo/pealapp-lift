package code.comet.util

import net.liftweb.util.StringHelpers
import net.liftweb.http.js.{JsMember, JsExp}

object JqVal {

  import StringHelpers._

  def apply(): JsExp with JsMember = new JsExp with JsMember {
    def toJsCmd = "val()"
  }

  def apply(content: String): JsExp with JsMember = new JsExp with JsMember {
    def toJsCmd = "val(" + content.encJs + ")"
  }
}
