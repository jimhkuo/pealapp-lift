package code.comet

import net.liftweb._
import http._
import util._
import net.liftweb.http.js.jquery.JqJE._
import net.liftweb.http.js.JsCmds._
import scala.xml.Text
import net.liftweb.http.js.{JsMember, JsExp}


class PealCometActor extends CometActor {
  var inputPolicies = ""

  def render = {
    this ! Init

    <form class="lift:form.ajax">
      <div>
        <h3>Input policies:</h3>
        <div>
          {SHtml.ajaxTextarea("", s => {
          inputPolicies = s; _Noop
        }, "id" -> "policies", "cols" -> "30", "rows" -> "20")}
        </div>
        <div>
          {SHtml.ajaxButton("Ajax Submit", () => {
          this ! Compute; _Noop
        }) ++
          SHtml.ajaxButton("Clear", () => {
            this ! Clear; _Noop
          })}
        </div>
        <div>
          <h3>Result:</h3>
          <div id="result"></div>
        </div>
      </div>
    </form>
  }

  override def lowPriority = {
    case Init =>
    case Compute => partialUpdate(JqId("result") ~> JqHtml(Text("got: " + inputPolicies)))
    case Clear => partialUpdate(JqId("policies") ~> JqVal(""))
  }
}

case object Init

case object Clear

case object Compute

object JqVal {

  import StringHelpers._

  def apply(): JsExp with JsMember = new JsExp with JsMember {
    def toJsCmd = "val()"
  }

  def apply(content: String): JsExp with JsMember = new JsExp with JsMember {
    def toJsCmd = "val(" + content.encJs + ")"
  }
}
