package build

import sbt._
import Keys._

object PealBuild extends Build {

  lazy val actToPeal = Project(id = "ActToPeal", base = file("ActToPeal"))

  lazy val reporter = Project(id = "ReportMaker", base = file("ReportMaker")) dependsOn (domain % "test->test;compile->compile")

  lazy val expressionBuilder = Project(id = "ExpressionBuilder", base = file("ExpressionBuilder")) dependsOn (domain % "test->test;compile->compile")

  lazy val runner = Project(id = "ExperimentRunner", base = file("ExperimentRunner")) dependsOn(verifier, generator, synthesiser, z3)

  lazy val root = Project(id = "PealApp-lift", base = file(".")) aggregate(generator, domain, parser, synthesiser, verifier, specialiser, expressionBuilder) dependsOn(verifier % "test->test;compile->compile", generator % "test->test;compile->compile", synthesiser % "test->test;compile->compile", domain % "test->test;compile->compile", z3 % "test->test;compile->compile", specialiser % "test->test;compile->compile", expressionBuilder % "test->test;compile->compile", actToPeal % "test->test;compile->compile")

  lazy val verifier = Project(id = "PealResultVerifier", base = file("PealResultVerifier")) dependsOn(synthesiser, parser, domain % "test->test;compile->compile", z3)

  lazy val specialiser = Project(id = "PealPolicySpecialisation", base = file("PealPolicySpecialisation")) dependsOn (verifier)

  lazy val synthesiser = Project(id = "PealSynthesiser", base = file("PealSynthesiser")) dependsOn(parser, domain % "test->test;compile->compile")

  lazy val maximisePSet = Project(id = "PSetMaximiser", base = file("PSetMaximiser")) dependsOn(parser, domain % "test->test;compile->compile",synthesiser, z3, parser, verifier)

  lazy val minimisePSet = Project(id = "PSetMinimiser", base = file("PSetMinimiser")) dependsOn(parser, domain % "test->test;compile->compile",synthesiser, z3, parser, verifier, maximisePSet)

  lazy val z3 = Project(id = "Z3Wrapper", base = file("Z3Wrapper")) dependsOn(domain, parser)

  lazy val generator = Project(id = "PealModelGenerator", base = file("PealModelGenerator")) dependsOn(parser, domain % "test->test;compile->compile")

  lazy val parser = Project(id = "PealParser", base = file("PealParser")) dependsOn (domain % "test->test;compile->compile")

  lazy val domain = Project(id = "Domain", base = file("Domain"))

}
