package peal.domain.z3.wrapper

object And {
  def apply(args: PealAst *) : PealAst = new And(args:_*)
  def apply(term: String) : PealAst = Term(term)
}

class And(args: PealAst *) extends PealAst {

  override def toString = args.size match {
    case 0 => "true"
    case 1 => args(0).toString
    case _ => "(and " + args.map(_.toString).mkString(" ") + ")"
  }
}