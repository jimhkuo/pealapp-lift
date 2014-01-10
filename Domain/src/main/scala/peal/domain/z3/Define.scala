package peal.domain.z3

class Define(val name: String, val objectType: String, val value: String) {
  override def toString = name + " (" + objectType + ") = " + value
}
