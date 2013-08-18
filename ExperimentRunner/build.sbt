import AssemblyKeys._

assemblySettings

test in assembly := {}

mainClass in assembly := Some("peal.runner.Main")

//use linux jar in assembly
unmanagedJars in Compile <++= baseDirectory map { base =>
	val baseDirectories = (base / "../lib_linux")
	val customJars = (baseDirectories ** "*.jar")
	customJars.classpath
}

excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
  cp filter {_.data.getName.endsWith("-mac.jar")}
}

name := "ExperimentRunner"

version := "0.1-ez-plus"

scalaVersion := "2.10.1"

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"


//Needed to get ScalaZ3 test to run repeatedly in sbt
fork in Test := true

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.1.2"


