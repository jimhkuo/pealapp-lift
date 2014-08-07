package peal.synthesis.analysis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit


class AlwaysFalseTest extends ShouldMatchersForJUnit {

   @Test
   def testCreateZ3SMTInput() {

     val expected =
       "(declare-const always_false_name1 Bool)\n" +
       "(assert (= always_false_name1 cond1))\n" +
       "(assert always_false_name1)\n" +
       "(check-sat)\n" +
       "(get-model)\n"
     new AlwaysFalse("name1", "cond1").z3SMTInput should be(expected)
   }
 }
