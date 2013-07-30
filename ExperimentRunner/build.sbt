import AssemblyKeys._

assemblySettings

test in assembly := {}

mainClass in assembly := Some("test.Main")

name := "ExperimentRunner"

version := "0.1"

scalaVersion := "2.10.1"

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"


//Needed to get ScalaZ3 test to run repeatedly in sbt
fork in Test := true

//AKKA and spray stuff
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.1.2"

//seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

