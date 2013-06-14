package code.comet

import net.liftweb._
import http._
import util._
import net.liftweb.http.js.jquery.JqJE._
import net.liftweb.http.js.JsCmds._
import scala.xml.Text
import net.liftweb.http.js.{JsMember, JsExp}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}


class PealCometActor extends CometActor {
  var inputPolicies = "cond = pSet <= 0.5\n" +
    "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
    "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
    "pSet = max(b1, b2)"

  def render = {
    this ! Init

    def f(a: Int)(b: Int) = a + b
    val x = f(10)(_)

    <form class="lift:form.ajax">
      <div>
        <h3>Input policies:</h3>
        <div>
          {//            SHtml.textarea("foo", inputPolicies = _)
          //            SHtml.textarea("foo", s => inputPolicies = s)
          //            SHtml.textarea("foo", "prefix " + _)}{SHtml.ajaxTextarea(inputPolicies, s => {
          inputPolicies = s;
          _Noop
        }, "id" -> "policies", "cols" -> "30", "rows" -> "20")}
        </div>
        <div>
          {SHtml.ajaxButton("Ajax Submit", () => {
          this ! Compute;
          _Noop
        }) ++
          SHtml.ajaxButton("Clear", () => {
            this ! Clear;
            _Noop
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
    case Compute => onCompute(inputPolicies)
    case Result(output) => partialUpdate(JqId("result") ~> JqHtml(Text(output)))
    case Error(message) => partialUpdate(JqId("result") ~> JqHtml(Text(message)))
    case Clear => partialUpdate(JqId("policies") ~> JqVal(""))
  }

  private def onCompute(input: String) {
    try {
      val pealProgrmParser = getParser(input)
      pealProgrmParser.program()
      val result = pealProgrmParser.pSet.synthesis
      this ! Result(result)
    } catch {
      case e: Exception => this ! Error(e.getMessage)
    }
  }

  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }
}

case object Init

case object Clear

case object Compute

case class Result(output: String)

case class Error(output: String)

object JqVal {

  import StringHelpers._

  def apply(): JsExp with JsMember = new JsExp with JsMember {
    def toJsCmd = "val()"
  }

  def apply(content: String): JsExp with JsMember = new JsExp with JsMember {
    def toJsCmd = "val(" + content.encJs + ")"
  }
}
