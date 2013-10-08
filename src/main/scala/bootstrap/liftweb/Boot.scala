package bootstrap.liftweb

import net.liftweb.http.{Req, StreamingResponse, LiftRules}
import net.liftweb.sitemap.{SiteMap, Menu}
import net.liftweb.common.Full
import scala.Nil
import code.lib.Z3SMTData

class Boot {
  def boot {
    LiftRules.addToPackages("code")

    val entries = List(
      Menu.i("PEALT tool") / "index",
//      Menu.i("Debug info") / "debug",
      Menu.i("About PEALT") / "aboutpealt",
      Menu.i("User guide") / "userguide"
      //      Menu.i("Download") / "download"
      //    ,  Menu(Loc("Static", Link(List("static"), true, "/static/index"), "Some static page"))
    )
    LiftRules.setSiteMap(SiteMap(entries: _*))

    LiftRules.ajaxStart = Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    LiftRules.ajaxEnd = Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    LiftRules.dispatch.append {
      case req@Req(List("download"), _, _) => {
        val result = Z3SMTData.is
        val headers = "Content-type" -> "text/plain" :: "Content-length" -> result.length.toString :: "Content-disposition" -> "attachment; filname=result.txt" :: Nil
        () => Full(StreamingResponse(
          new java.io.ByteArrayInputStream(result.getBytes("utf-8")),
          () => {},
          result.length,
          headers, Nil, 200)
        )
      }
    }
  }
}