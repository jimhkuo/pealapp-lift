package peal.domain.z3


object False {
   def apply() : PealAst = new False()
 }

class False extends PealAst {
  override def toString = "false"
}
