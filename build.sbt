import AssemblyKeys._

assemblySettings

test in assembly := {}

mainClass in assembly := Some("bootstrap.liftweb.Start")

name := "PealApp-lift"

version := "3.0.2"

scalaVersion := "2.10.4"

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"


//Lift stuff
seq(com.github.siasia.WebPlugin.webSettings :_*)

libraryDependencies ++= {
  val liftVersion = "2.5-RC5"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "org.eclipse.jetty" % "jetty-webapp" % "8.1.10.v20130312"  % "container,compile,test",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,compile" artifacts Artifact("javax.servlet", "jar", "jar")
  )
}

//the following two blocks are needed for assembly
resourceGenerators in Compile <+= (resourceManaged, baseDirectory) map
{ (managedBase, base) =>
  val webappBase = base / "src" / "main" / "webapp"
  for {
    (from, to) <- webappBase ** "*" x rebase(webappBase, managedBase /
      "main" / "webapp")
  } yield {
    Sync.copy(from, to)
    to
  }
}

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case "about.html" => MergeStrategy.rename
    case "rootdoc.txt" => MergeStrategy.rename
    case x => old(x)
  }
}

//Needed to get ScalaZ3 test to run repeatedly in sbt
fork in Test := true

//AKKA and spray stuff
//resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
//resolvers += "spray repo" at "http://repo.spray.io"
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.1.2"
//libraryDependencies += "io.spray" % "spray-can" % "1.1-M7"
//libraryDependencies += "io.spray" % "spray-routing" % "1.1-M7"
//one-jar stuff
//seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)
