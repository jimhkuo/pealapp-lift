package bootstrap.liftweb

import java.net.URL

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.webapp.WebAppContext
import peal.util.ConsoleLogger

object Start extends App {

  val connector = new SelectChannelConnector()
  if (args.length > 0) {
    connector.setPort(args(0).toInt)
  }
  else {
    connector.setPort(8080)
  }
  val server = new Server()
  server.addConnector(connector)

  val webctx = new WebAppContext
  val resource: URL = webctx.getClass.getClassLoader.getResource("webapp")
  val webappDirFromJar = resource.toExternalForm

  webctx.setWar(webappDirFromJar)
  webctx.setContextPath("/")
  webctx.setInitParameter("org.eclipse.jetty.servlet.SessionCookie", "JSESSIONID"+ connector.getPort)

  server.setHandler(webctx)
  server.start()
  server.join()
}
