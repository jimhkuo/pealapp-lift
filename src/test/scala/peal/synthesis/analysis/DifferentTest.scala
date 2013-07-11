package peal.synthesis.analysis

import org.junit.Test
import org.scalatest.junit.ShouldMatchersForJUnit


class DifferentTest extends ShouldMatchersForJUnit {

   @Test
   def testCreateZ3SMTInput() {

     val expected = "(push)\n" +
       "(declare-const difference_name1 Bool)\n" +
       "(assert (= difference_name1 (or (and cond1 (not cond2)) (and (not cond1) cond2))))\n" +
       "(check-sat)\n" +
       "(get-model)\n" +
       "(pop)\n"
     new Different("name1", "cond1", "cond2").z3SMTInput should be(expected)
   }
 }
