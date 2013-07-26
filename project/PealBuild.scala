package build

import sbt._
import Keys._

object PealBuild extends Build {
  lazy val root = Project(id = "PealApp-lift", base = file(".")) aggregate (domain, pmg) dependsOn(domain, pmg)

  lazy val pmg = Project(id = "PealModelGenerator", base = file("PealModelGenerator")) dependsOn(domain)

  lazy val domain = Project(id = "Domain", base = file("Domain"))
}