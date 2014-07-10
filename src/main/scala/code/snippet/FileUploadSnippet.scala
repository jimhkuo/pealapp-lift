package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml._
import net.liftweb.http.FileParamHolder
import net.liftweb.common.{Loggable, Full, Empty, Box}
import peal.util.ConsoleLogger


class FileUploadSnippet extends Loggable {

  def render = {

    var upload : Box[FileParamHolder] = Empty

    def processForm() = upload match {
      case Full(FileParamHolder(_, mimeType, fileName, file)) =>
        ConsoleLogger.log("%s of type %s is %d bytes long" format (fileName, mimeType, file.length) )
        val chars = new String(file)
        ConsoleLogger.log1(chars)

      case _ => ConsoleLogger.log("No file?")
    }

    "#file" #> fileUpload(f => upload = Full(f)) &
      "type=submit" #> onSubmitUnit(processForm)
  }
}
