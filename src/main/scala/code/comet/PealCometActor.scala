package code.comet

import net.liftweb._
import http._
import net.liftweb.http.js.jquery.JqJE._
import net.liftweb.http.js.JsCmds._
import scala.xml.Text
import org.antlr.runtime.{CommonTokenStream, ANTLRStringStream}
import peal.antlr.{PealProgramParser, PealProgramLexer}
import net.liftweb.common.Loggable
import scala.collection.JavaConversions._
import z3.scala.{Z3Config, Z3Context}
import code.comet.util._


class PealCometActor extends CometActor with Loggable {
  val defaultInput = "cond = pSet <= 0.5\n" +
    "b1 = min ((q1 0.2) (q2 0.4) (q3 0.9)) default 1\n" +
    "b2 = + ((q4 0.1) (q5 0.2) (q6 0.2)) default 0\n" +
    "pSet = max(b1, b2)"
  var inputPolicies = defaultInput
  var majorityVotingCount = 10

  def render = {
    this ! Init

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
          {SHtml.ajaxButton("Reset to sample policies", () => {
          this ! Reset
          _Noop
        }) ++
          SHtml.ajaxButton("Clear policies", () => {
            this ! Clear
            _Noop
          })}{SHtml.ajaxButton("Majority Voting", () => {
          this ! MajorityVoting
          _Noop
        })}{SHtml.ajaxText(majorityVotingCount.toString, s => {
          majorityVotingCount = s.toInt
          _Noop
        }, "id" -> "n", "size" -> "10")}
        </div>
        <div>
          {SHtml.ajaxButton("Synthesise (and show results)", () => {
          this ! Display
          _Noop
        }) ++ SHtml.ajaxButton("Synthesise (and download)", () => {
          this ! Prepare
          _Noop
        })}
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
    case Display =>
      val (predicateNames, body, lapseTime) = onCompute(inputPolicies)
      onDisplay(predicateNames, body)
    case Prepare =>
      partialUpdate(JqId("result") ~> JqHtml(Text("Synthesising... Please wait...")))
      this ! Download
    case Download =>
      val (predicateNames, body, lapseTime) = onCompute(inputPolicies)
      onDownload(predicateNames, body, lapseTime)
    case File(result, lapseTime) =>
      myData.set(result)
      this ! Result(<p>Output prepared, lapse time:
        <span style="color:red;font-weight: bold;">
          {"%.2f".format(lapseTime.toDouble / 1000000)}
        </span>
        ms, please click
        <a href="download">here</a>
        to download the file</p>)
    case Result(output) => partialUpdate(JqId("result") ~> JqHtml(output))
    case Message(message) => partialUpdate(JqId("result") ~> JqHtml(Text(message)))
    case Clear =>
      this ! Message("")
      partialUpdate(JqId("policies") ~> JqVal(""))
    case MajorityVoting =>
      this ! Message("")
      inputPolicies = "cond = 0.5 < pSet\nb1 = + (" +
        (for (i <- 0 until majorityVotingCount) yield "(q" + i + " " + "%.3f".format(1.0 / majorityVotingCount) + ")").mkString("") +
        " ) default 0\npSet = b1"
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
    case Reset =>
      this ! Message("")
      inputPolicies = defaultInput
      partialUpdate(JqId("policies") ~> JqVal(inputPolicies))
  }

  private def getParser(input: String) = {
    val charStream = new ANTLRStringStream(input)
    val lexer = new PealProgramLexer(charStream)
    val tokenStream = new CommonTokenStream(lexer)
    new PealProgramParser(tokenStream)
  }

  private def onCompute(input: String) : (Seq[String], String, Long) ={
    val pealProgrmParser = getParser(input)
    val z3 = new Z3Context(new Z3Config("MODEL" -> true))

    try {
      pealProgrmParser.program()
      val predicateNames : Seq[String] = pealProgrmParser.pols.values().flatMap(pol => pol.rules).map(r => r.q.name).toSeq.distinct
      val constsMap = predicateNames.toSeq.distinct.map(t => (t, z3.mkBoolConst(t))).toMap
      val start = System.nanoTime()
      val body = pealProgrmParser.pSet.phiZ3SMTString(z3, constsMap)
      val lapseTime = System.nanoTime() - start
      (predicateNames, body, lapseTime)
    } catch {
      case e2: NullPointerException => dealWithIt(e2)
      case e1: Throwable => dealWithIt(e1)
    }
    finally {
      z3.delete()
    }
  }

  private def onDisplay(predicateNames: Seq[String], body: String) {
      val declarations = for (name <- predicateNames) yield <p>
        {"(declare-const " + name + " Bool)"}
      </p>
      val result = <p>
        {declarations}{body}<br/>
        (check-sat)
        <br/>
        (get-model)</p>
      this ! Result(result)
  }

  private def onDownload(predicateNames: Seq[String], body: String, lapseTime: Long) {
      val declarations = for (name <- predicateNames) yield "(declare-const " + name + " Bool)\n"
      val result = declarations.mkString("") + body + "\n" + "(check-sat)\n(get-model)"
      this ! File(result, lapseTime)
  }

  private def dealWithIt(e: Throwable) = {
    this ! Message(e.getMessage)
    throw e
  }
}







