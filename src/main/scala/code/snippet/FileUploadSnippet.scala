package code.snippet

import code.comet.CometServer
import code.lib.{UploadFile, Z3SMTData}
import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml._
import net.liftweb.http.{S, FileParamHolder}
import net.liftweb.common.{Loggable, Full, Empty, Box}
import peal.util.ConsoleLogger


class FileUploadSnippet extends Loggable {

  def render = {

    var upload: Box[FileParamHolder] = Empty

    def processForm() = upload match {
      case Full(FileParamHolder(_, mimeType, fileName, file)) =>
        ConsoleLogger.log1("%s of type %s is %d bytes long" format(fileName, mimeType, file.length))
        val content = new String(file)
        val id = for (sess <- S.session) yield sess.uniqueId
        CometServer ! UploadFile(id.toList(0), content)
      //        S.redirectTo("/")
      case _ => ConsoleLogger.log("No file?")
    }

    "#file" #> fileUpload(f => upload = Full(f)) &
      "type=submit" #> onSubmitUnit(processForm)
  }
}
