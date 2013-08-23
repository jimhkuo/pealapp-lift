package peal.domain.z3.wrapper

object Not {
   def apply(arg : PealAst) : PealAst = new Not(arg)
 }

class Not(arg : PealAst) extends PealAst {
  override def toString = "(not " + arg.toString + ")"
}

