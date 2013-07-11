package peal.synthesis.analysis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit


class EquivalenceTest extends ShouldMatchersForJUnit {

   @Test
   def testCreateZ3SMTInput() {

     val expected = "(push)\n" +
       "(declare-const equivalence_name1 Bool)\n" +
       "(assert (= equivalence_name1 (and (implies cond1 cond2) (implies cond2 cond1))))\n" +
       "(assert equivalence_name1)\n" +
       "(check-sat)\n" +
       "(get-model)\n" +
       "(pop)\n"
     new Equivalence("name1", "cond1", "cond2").z3SMTInput should be(expected)
   }
 }
