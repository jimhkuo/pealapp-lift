package bootstrap.liftweb

import net.liftweb.http.LiftRules
import net.liftweb.sitemap.{SiteMap, Menu}
import net.liftweb.common.Full

class Boot {
  def boot {
    LiftRules.addToPackages("code")

    val entries = List(
      Menu.i("Peal") / "index"
      //      Menu.i("Home") / "peal",
      //      Menu.i("Peal") / "peal",
      , Menu.i("About us") / "aboutus"
      //    ,  Menu(Loc("Static", Link(List("static"), true, "/static/index"), "Some static page"))
    )
    LiftRules.setSiteMap(SiteMap(entries: _*))

    LiftRules.ajaxStart = Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    LiftRules.ajaxEnd = Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
  }
}