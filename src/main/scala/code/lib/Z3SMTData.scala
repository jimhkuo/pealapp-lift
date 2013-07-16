package code.lib

import net.liftweb.http.SessionVar
import z3.scala.{Z3Config, Z3Context}

object Z3SMTData extends SessionVar[String]("")
object MyZ3Context extends SessionVar[Z3Context](new Z3Context(new Z3Config("MODEL" -> true)))
