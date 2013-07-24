package build

import sbt._
import Keys._

object PealBuild extends Build {
  lazy val root = Project(id = "PealApp-lift", base = file(".")) dependsOn (pmg)

  lazy val pmg = Project(id = "child1", base = file("child1"))
}