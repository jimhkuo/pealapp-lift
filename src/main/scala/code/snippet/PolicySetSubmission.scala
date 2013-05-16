package code
package snippet

import net.liftweb._
import http._
import js._
import JsCmds._
import comet.PealAnalyser

object PolicySetSubmission {
  def render = SHtml.onSubmit(s => {
    PealAnalyser ! s
    SetValById("chat_in", "")
  })
}