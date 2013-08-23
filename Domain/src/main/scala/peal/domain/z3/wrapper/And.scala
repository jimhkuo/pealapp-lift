package peal.domain.z3.wrapper

object And {
  def apply(args: Z3AST *) : Z3AST = new And(args:_*)
  def apply(term: String) : Z3AST = Term(term)
}

class And(args: Z3AST *) extends Z3AST {

  override def toString = args.size match {
    case 0 => "true"
    case 1 => args(0).toString
    case _ => "(and " + args.map(_.toString).mkString(" ") + ")"
  }
}