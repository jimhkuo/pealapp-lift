package peal.domain.z3.wrapper

object Term {
   def apply(term: String) : Z3AST = new Term(term)
 }

class Term(term : String) extends Z3AST {
  override def toString = term
}