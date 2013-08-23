package peal.domain.z3


object True {
   def apply() : PealAst = new True()
 }

class True extends PealAst {
  override def toString = "false"
}
