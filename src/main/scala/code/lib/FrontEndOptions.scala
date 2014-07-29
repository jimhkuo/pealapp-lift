package code.lib

import net.liftweb.http.SessionVar

trait DisplayFormat

object DecimalFormat extends DisplayFormat {
  override def toString = "DecimalFormat"
}

object RationalFormat extends DisplayFormat {
  override def toString = "RationalFormat"
}

object Both extends DisplayFormat {
  override def toString = "Both"
}

object PealInputData extends SessionVar[String]("")

object VcOption extends SessionVar[Boolean](false)

object DnOption extends SessionVar[DisplayFormat](Both)
