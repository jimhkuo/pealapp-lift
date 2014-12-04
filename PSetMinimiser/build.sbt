import AssemblyKeys._

assemblySettings

test in assembly := {}

mainClass in assembly := Some("peal.minimise.Main")

name := "PSetMinimiser"

version := "0.2"

scalaVersion := "2.11.4"

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.2" % "test"
