package peal.synthesis.analysis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit


class DifferentTest extends ShouldMatchersForJUnit {

   @Test
   def testCreateZ3SMTInput() {

     val expected =
       "(declare-const different_name1 Bool)\n" +
       "(assert (= different_name1 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n" +
       "(assert different_name1)\n" +
       "(check-sat)\n" +
       "(get-model)\n"
     new Different("name1", "cond1", "cond2").z3SMTInput should be(expected)
   }
 }
