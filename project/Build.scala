package build

import sbt._
import Keys._

object PealBuild extends Build {
  lazy val root = Project(id = "PealApp-lift", base = file(".")) dependsOn (pmg, child1)

  lazy val pmg = Project(id = "PealModelGenerator", base = file("PealModelGenerator"))

  lazy val child1 = Project(id = "child1", base = file("child1"))
}