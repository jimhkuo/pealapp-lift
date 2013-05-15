name := "PealApp-lift"

version := "0.1"

scalaVersion := "2.10.1"

//resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

//resolvers += "spray repo" at "http://repo.spray.io"

//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.1.2"

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

//libraryDependencies += "io.spray" % "spray-can" % "1.1-M7"

//libraryDependencies += "io.spray" % "spray-routing" % "1.1-M7"

seq(com.github.siasia.WebPlugin.webSettings :_*)

libraryDependencies ++= {
  val liftVersion = "2.5-RC5"
  Seq(
	"org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container,compile",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,compile" artifacts Artifact("javax.servlet", "jar", "jar"),
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "org.eclipse.jetty" % "jetty-webapp" % "8.1.10.v20130312"  % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" %
      "container,compile" artifacts Artifact("javax.servlet", "jar", "jar")
  )
}

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

