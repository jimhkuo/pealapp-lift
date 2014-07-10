package bootstrap.liftweb

import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import java.net.URL

import peal.util.ConsoleLogger

object Start extends App {

  ConsoleLogger.enable(1)

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

  server.setHandler(webctx)
  server.start
  server.join
}
