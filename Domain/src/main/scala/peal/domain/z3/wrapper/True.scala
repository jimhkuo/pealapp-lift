package peal.domain.z3.wrapper

object True {
   def apply() : PealAst = new True()
 }

class True extends PealAst {
  override def toString = "false"
}
