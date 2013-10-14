package build

import sbt._
import Keys._

object PealBuild extends Build {

  lazy val runner = Project(id = "ExperimentRunner", base = file("ExperimentRunner")) dependsOn(generator, synthesiser, inputChecker)

  lazy val root = Project(id = "PealApp-lift", base = file(".")) aggregate (generator, domain, parser, synthesiser, inputChecker) dependsOn(inputChecker, generator, synthesiser, domain % "test->test;compile->compile")

  lazy val synthesiser = Project(id = "PealSynthesiser", base = file("PealSynthesiser")) dependsOn(parser, domain % "test->test;compile->compile")

  lazy val generator = Project(id = "PealModelGenerator", base = file("PealModelGenerator")) dependsOn (parser, domain % "test->test;compile->compile")

  lazy val inputChecker = Project(id = "InputChecker", base = file("InputChecker")) dependsOn (parser, domain % "test->test;compile->compile")

  lazy val parser = Project(id = "PealParser", base = file("PealParser")) dependsOn (domain % "test->test;compile->compile")

  lazy val domain = Project(id = "Domain", base = file("Domain"))

}