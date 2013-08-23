package peal.domain.z3.wrapper

object Term {
   def apply(term: String) : PealAst = new Term(term)
 }

class Term(term : String) extends PealAst {
  override def toString = term
}