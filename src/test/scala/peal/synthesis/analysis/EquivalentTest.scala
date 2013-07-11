package peal.synthesis.analysis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit


class EquivalentTest extends ShouldMatchersForJUnit {

   @Test
   def testCreateZ3SMTInput() {

     val expected = "(push)\n" +
       "(declare-const equivalent_name1 Bool)\n" +
       "(assert (= equivalent_name1 (and (implies cond1 cond2) (implies cond2 cond1))))\n" +
       "(check-sat)\n" +
       "(get-model)\n" +
       "(pop)\n"
     new Equivalent("name1", "cond1", "cond2").z3SMTInput should be(expected)
   }
 }
