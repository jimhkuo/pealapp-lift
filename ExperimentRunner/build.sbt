import AssemblyKeys._

assemblySettings

test in assembly := {}

mainClass in assembly := Some("peal.runner.Main")

name := "ExperimentRunner"

version := "0.5-certify-all"

scalaVersion := "2.11.4"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies +=  "com.typesafe.akka" %% "akka-actor" % "2.3.7"

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.2" % "test"

//Needed to get ScalaZ3 test to run repeatedly in sbt
fork in Test := true



//libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.2.0" cross CrossVersion.full

