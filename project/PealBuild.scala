package build

import sbt._
import Keys._

object PealBuild extends Build {

  lazy val runner = Project(id = "ExperimentRunner", base = file("ExperimentRunner")) dependsOn(pmg)

  lazy val root = Project(id = "PealApp-lift", base = file(".")) aggregate (domain, pmg, parser) dependsOn(domain, pmg, parser)

  lazy val parser = Project(id = "PealParser", base = file("PealParser")) dependsOn(domain)

  lazy val pmg = Project(id = "PealModelGenerator", base = file("PealModelGenerator")) dependsOn(domain)

  lazy val domain = Project(id = "Domain", base = file("Domain"))
}