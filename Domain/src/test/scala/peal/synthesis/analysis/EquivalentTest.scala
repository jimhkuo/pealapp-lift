package peal.synthesis.analysis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit


class EquivalentTest extends ShouldMatchersForJUnit {

   @Test
   def testCreateZ3SMTInput() {

     val expected = "(push)\n" +
       "(declare-const equivalent_name1 Bool)\n" +
       "(assert (= equivalent_name1 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n" +
       "(assert equivalent_name1)\n" +
       "(check-sat)\n" +
       "(get-model)\n" +
       "(pop)\n"
     new Equivalent("name1", "cond1", "cond2").z3SMTInput should be(expected)
   }
 }
