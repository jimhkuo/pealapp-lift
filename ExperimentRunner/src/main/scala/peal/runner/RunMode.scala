package peal.runner

trait RunMode

object Explicit extends RunMode {
  override def toString = "explicit"
}

object Symbolic extends RunMode {
  override def toString = "symbolic"
}

object NewSynthesis extends RunMode {
  override def toString = "new"
}

