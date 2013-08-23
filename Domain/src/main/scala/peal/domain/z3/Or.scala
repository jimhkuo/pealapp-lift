package peal.domain.z3


object Or {
   def apply(args: PealAst *) : PealAst = new Or(args:_*)
   def apply(term: String) : PealAst = Term(term)
 }

class Or(args: PealAst *) extends PealAst {
  override def toString = args.size match {
    case 0 => "false"
    case 1 => args(0).toString
    case _ => "(or " + args.map(_.toString).mkString(" ") + ")"
  }
}
