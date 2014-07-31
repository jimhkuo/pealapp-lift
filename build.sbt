import AssemblyKeys._

assemblySettings

test in assembly := {}

mainClass in assembly := Some("bootstrap.liftweb.Start")

name := "PealApp-lift"

version := "3.1.8"

scalaVersion := "2.10.4"

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

//Lift stuff
resolvers ++= Seq("snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases"        at "http://oss.sonatype.org/content/repositories/releases"
)

seq(webSettings :_*)

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/main/webapp" }

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "2.5.1"
  Seq(
    "net.liftweb"       %% "lift-webkit"        % liftVersion        % "compile",
    "net.liftmodules"   %% "lift-jquery-module_2.5" % "2.4",
    "org.eclipse.jetty" % "jetty-webapp" % "8.1.10.v20130312"  % "container,compile,test",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,compile" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"    % "logback-classic"     % "1.0.6",
    "org.specs2"        %% "specs2"             % "1.14"            % "test"
  )
}

//the following is required to copy webapp to the right place in sbt
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

//the following block are needed for assembly
mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case "about.html" => MergeStrategy.rename
    case "rootdoc.txt" => MergeStrategy.rename
    case x => old(x)
  }
}

//Needed to get ScalaZ3 test to run repeatedly in sbt
fork in Test := true

