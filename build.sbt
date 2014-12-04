import AssemblyKeys._

assemblySettings

test in assembly := {}

mainClass in assembly := Some("bootstrap.liftweb.Start")

name := "PealApp-lift"

version := "3.2.7"

scalaVersion := "2.11.4"

//Lift stuff
resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases" at "http://oss.sonatype.org/content/repositories/releases"
)

webSettings

unmanagedResourceDirectories in Test <+= (baseDirectory) {
  _ / "src/main/webapp"
}

unmanagedJars in Compile ++= {
  val base = baseDirectory.value
  val baseDirectories = (base / "PealParser" / "lib")
  val customJars = (baseDirectories ** "*.jar")
  customJars.classpath
}

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "2.6-RC2"
  //val jettyVersion = "9.2.3.v20140905"
  val jettyVersion = "8.1.10.v20130312"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container, test, compile",
    "ch.qos.logback" % "logback-classic" % "1.1.2" % "compile",
    "org.scalatest" %% "scalatest" % "2.2.2" % "test",
    "com.novocode" % "junit-interface" % "0.8" % "test->default",
    "com.netflix.rxjava" % "rxjava-scala" % "0.20.3",
    "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
    //"org.eclipse.jetty"             % "jetty-plus"                 % jettyVersion           % "container, test, compile",
    //"org.specs2" %% "specs2-core" % "2.4.14" % "test"
  )
}

//the following is required to copy webapp to the right place in sbt
resourceGenerators in Compile <+= (resourceManaged, baseDirectory) map { (managedBase, base) =>
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
mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) => {
  case "about.html" => MergeStrategy.rename
  case "rootdoc.txt" => MergeStrategy.rename
  case x => old(x)
}
}

//Needed to get ScalaZ3 test to run repeatedly in sbt
fork in Test := true
