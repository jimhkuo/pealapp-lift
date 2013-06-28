package bootstrap.liftweb

import net.liftweb.http.{Req, StreamingResponse, LiftRules}
import net.liftweb.sitemap.{SiteMap, Menu}
import net.liftweb.common.Full
import scala.Nil

class Boot {
  def boot {
    LiftRules.addToPackages("code")

    val entries = List(
      Menu.i("Peal") / "index",
      //      Menu.i("Home") / "peal",
      //      Menu.i("Peal") / "peal",
      Menu.i("About us") / "aboutus"
      //    ,  Menu(Loc("Static", Link(List("static"), true, "/static/index"), "Some static page"))
    )
    LiftRules.setSiteMap(SiteMap(entries: _*))

    LiftRules.ajaxStart = Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    LiftRules.ajaxEnd = Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    val headers = ("Content-type" -> "text/plain") :: ("Content-length" -> "2") :: ("Content-disposition" -> "attachment; filname=result.txt") :: Nil

    LiftRules.dispatch.append {
      case req @ Req(List("files", filename), _, _) => {
        () =>  Full(StreamingResponse(
          new java.io.ByteArrayInputStream("hi".getBytes("utf-8")),
          () => {},
          2,
          headers, Nil, 200)
        )
      }
    }
  }
}