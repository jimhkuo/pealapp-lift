package build

import sbt._
import Keys._

object PealBuild extends Build {
  lazy val root = Project(id = "PealApp-lift", base = file(".")) aggregate (pmg) dependsOn(pmg)

  lazy val pmg = Project(id = "PealModelGenerator", base = file("PealModelGenerator"))
}