package build

import sbt._
import Keys._

object PealBuild extends Build {

//  lazy val runner = Project(id = "ExperimentRunner", base = file("ExperimentRunner")) dependsOn(generator, parser)

  lazy val root = Project(id = "PealApp-lift", base = file(".")) dependsOn(generator, domain)

//  lazy val synthesiser = Project(id = "PealSynthesiser", base = file("PealSynthesiser")) dependsOn(parser, domain)

//  lazy val parser = Project(id = "PealParser", base = file("PealParser")) dependsOn (domain)

  lazy val generator = Project(id = "PealModelGenerator", base = file("PealModelGenerator")) dependsOn (domain)

  lazy val domain = Project(id = "Domain", base = file("Domain"))
}