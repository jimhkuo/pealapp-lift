package peal.domain.z3

class Assignment(val name: String, val objectType: String, val value: String) {
  override def toString = name + " (" + objectType + ") = " + value
}
