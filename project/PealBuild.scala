package build

import sbt._
import Keys._

object PealBuild extends Build {

  def standardSettings = Seq(
    exportJars := true
  ) ++ Defaults.defaultSettings

  lazy val root = Project(id = "PealApp-lift", base = file(".")) aggregate (domain, pmg) dependsOn(domain, pmg)

  lazy val runner = Project(id = "ExperimentRunner", base = file("ExperimentRunner")) dependsOn(pmg)

  lazy val pmg = Project(id = "PealModelGenerator", base = file("PealModelGenerator")) dependsOn(domain)

  lazy val domain = Project(id = "Domain", base = file("Domain"))
}