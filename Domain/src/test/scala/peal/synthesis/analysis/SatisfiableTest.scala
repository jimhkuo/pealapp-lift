package peal.synthesis.analysis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit


class SatisfiableTest extends ShouldMatchersForJUnit {

   @Test
   def testCreateZ3SMTInput() {

     val expected =
       "(declare-const satisfiable_name1 Bool)\n" +
       "(assert (= satisfiable_name1 cond1))\n" +
       "(assert satisfiable_name1)\n" +
       "(check-sat)\n" +
       "(get-model)\n"
     new Satisfiable("name1", "cond1").z3SMTInput should be(expected)
   }
 }
