package peal.domain.z3.wrapper

object Or {
   def apply(args: Z3AST *) : Z3AST = new Or(args:_*)
   def apply(term: String) : Z3AST = new Or(term)
 }

class Or(args: Z3AST *) extends Z3AST {
  var t = "false"
  def this(term: String) = {
    this()
    t = term
  }

  override def toString = args.size match {
    case 0 => t
    case 1 => args(0).toString
    case _ => "(or " + args.map(_.toString).mkString(" ") + ")"
  }
}
