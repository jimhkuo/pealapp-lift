package bootstrap.liftweb

import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import java.net.URL

object Start extends App {

  val connector = new SelectChannelConnector()
  connector.setPort(8080)
  val server = new Server()
  server.addConnector(connector)

  val webctx = new WebAppContext
  val resource: URL = webctx.getClass.getClassLoader.getResource("webapp")
  val webappDirFromJar = resource.toExternalForm

  webctx.setWar(webappDirFromJar)
  webctx.setContextPath("/")


  server.setHandler(webctx)
  server.start
  server.join
}
