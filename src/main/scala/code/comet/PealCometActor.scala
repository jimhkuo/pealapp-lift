package code.comet

import net.liftweb._
import http._
import util._
import net.liftweb.http.js.jquery.JqJE._
import net.liftweb.http.js.JsCmds._
import scala.xml.{NodeSeq, Text}
import net.liftweb.http.js.{JsMember, JsExp}
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}
import net.liftweb.common.{Full, Loggable}
import scala.collection.immutable.Nil
import java.io.ByteArrayInputStream


class PealCometActor extends CometActor with Loggable {
  val defaultInput = "cond = pSet <= 0.5\n" +
    "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
    "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
    "pSet = max(b1, b2)"
  var inputPolicies = defaultInput
  var majorityVotingCount = 10

  def render = {
    this ! Init

    //sample code
    //    {SHtml.textarea("foo", inputPolicies = _)
    //      SHtml.textarea("foo", s => inputPolicies = s)
    //      SHtml.textarea("foo", "prefix " + _)}

    <form class="lift:form.ajax">
      <div>
        <h3>Input policies:</h3>
        <div>
          {SHtml.ajaxTextarea(inputPolicies, s => {
          inputPolicies = s
          _Noop
        }, "id" -> "policies", "cols" -> "30", "rows" -> "20")}
        </div>
        <div>
          {SHtml.ajaxButton("Submit", () => {
          this ! Compute
          _Noop
        }) ++
          SHtml.ajaxButton("Reset", () => {
            this ! Reset
            _Noop
          }) ++
          SHtml.ajaxButton("Majority Voting", () => {
            this ! MajorityVoting
            _Noop
          }) ++
          SHtml.ajaxButton("Clear", () => {
            this ! Clear
            _Noop
          }) ++
          SHtml.ajaxButton("Download", () => {
            this ! Download
            _Noop
          })}
        </div>
        <div>
          {SHtml.ajaxText("10", s => {
          majorityVotingCount = s.toInt
          _Noop
        }, "id" -> "n", "size" -> "10")}
        </div>
        <br/>
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
    case File(result) =>
      val headers = ("Content-type" -> "application/txt") :: ("Content-length" -> result.length.toString) :: ("Content-disposition" -> "attachment; filname=result.txt") :: Nil

      Full(StreamingResponse(
        new java.io.ByteArrayInputStream(result.getBytes("UTF-8")),
        () => {},
        result.length,
        headers, Nil, 200)
      )
    case Result(output) => partialUpdate(JqId("result") ~> JqHtml(output))
    case Error(message) => partialUpdate(JqId("result") ~> JqHtml(Text(message)))
    case Clear => partialUpdate(JqId("policies") ~> JqVal(""))
    case Download => onDownload(inputPolicies)
    case MajorityVoting =>
      inputPolicies = "cond = 0.5 < pSet\nb1 = + (" +
        (for (i <- 0 until majorityVotingCount) yield "(q" + i + " " + "%.3f".format(1.0 / majorityVotingCount) + ")").mkString("") +
        " ) default 0\npSet = b1"
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Reset =>
      inputPolicies = defaultInput
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
  }

  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  private def onCompute(input: String) {
    val pealProgrmParser = getParser(input)
    try {
      pealProgrmParser.program()
      val pSet = pealProgrmParser.pSet
      val result = <p>
        {pSet.z3SMTHeader}<br/>{pSet.phiZ3SMTString}<br/>
        (get-model)</p>
      this ! Result(result)
    } catch {
      case e1: Exception =>
        dealWithIt(e1)
    }
  }

  private def onDownload(input: String) {
    val pealProgrmParser = getParser(input)
    try {
      pealProgrmParser.program()
      val pSet = pealProgrmParser.pSet
      val result = pSet.z3SMTHeader + "\n" + pSet.phiZ3SMTString + "\n" + "(get-model)"

      this ! File(result)

      //      val headers = ("Content-type" -> "application/txt") :: ("Content-length" -> result.length.toString) :: ("Content-disposition" -> "attachment; filname=result.txt") :: Nil
      //
      //      Full(StreamingResponse(
      //        new java.io.ByteArrayInputStream(result.getBytes("UTF-8")),
      //        () => {},
      //        result.length,
      //        headers, Nil, 200)
      //      )
    }
    catch {
      case e1: Exception =>
        dealWithIt(e1)
    }
  }

  private def dealWithIt(e: Exception) {
    println("pl: " + e.getMessage)
    this ! Error(e.getMessage)
  }
}

case object Init

case object Clear

case object Download

case object Reset

case object MajorityVoting

case object Compute

case class Result(output: NodeSeq)

case class File(result: String)

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
