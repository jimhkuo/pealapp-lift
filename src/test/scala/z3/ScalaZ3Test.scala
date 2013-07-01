package z3

import org.junit.{Ignore, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import z3.scala.dsl._
import z3.scala.dsl.Operands._
import z3.scala._

class ScalaZ3Test extends ShouldMatchersForJUnit {

  @Ignore("doesn't work in batch mode")
  @Test
  def testPealModel() {
    //  (declare-const q1 Bool)
    //  (declare-const q2 Bool)
    //  (assert (and (or q1 q2) q1))
    //  (check-sat)
    //  (get-model)

    var z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val q1 = z3.mkBoolConst("q1")
    val q2 = z3.mkBoolConst("q2")
    val q1Orq2 = z3.mkOr(q1, q2)
    val solver = z3.mkSolver
    solver.assertCnstr(z3.mkAnd(q1Orq2, q1))

    var (sol, model) = solver.checkAndGetModel

    sol should equal (Some(true))
    model.evalAs[Boolean](q1) should be (Some(true))
    model.delete
    z3.delete()
    model = null
    z3 = null
  }

  @Ignore("doesn't work in batch mode")
  @Test
  def testComfusyLike() {
    var z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val i = z3.mkIntSort
    val h = z3.mkConst(z3.mkStringSymbol("h"), i)
    val m = z3.mkConst(z3.mkStringSymbol("m"), i)
    val s = z3.mkConst(z3.mkStringSymbol("s"), i)
    // builds a constant integer value from the CL arg.
    val t = z3.mkInt(1234, i)
    // more integer constants
    val z = z3.mkInt(0, i)
    val sx = z3.mkInt(60, i)
    // builds the constraint h*3600 + m * 60 + s == totSecs
    val cs1 = z3.mkEq(
      z3.mkAdd(
        z3.mkMul(z3.mkInt(3600, i), h),
        z3.mkMul(sx, m),
        s),
      t)
    // more constraints
    val cs2 = z3.mkAnd(z3.mkGE(h, z), z3.mkLT(h, z3.mkInt(24, i)))
    val cs3 = z3.mkAnd(z3.mkGE(m, z), z3.mkLT(m, sx))
    val cs4 = z3.mkAnd(z3.mkGE(s, z), z3.mkLT(s, sx))

    val solver = z3.mkSolver
    solver.assertCnstr(z3.mkAnd(cs1, cs2, cs3, cs4))

    // attempting to solve the constraints
    var (sol, model) = solver.checkAndGetModel

    sol should equal(Some(true))
    model.evalAs[Int](h) should equal(Some(0))
    model.evalAs[Int](m) should equal(Some(20))
    model.evalAs[Int](s) should equal(Some(34))

    model.delete
    z3.delete
    model = null
    z3 = null
    System.gc()
  }

  @Ignore("doesn't work in batch mode")
  @Test
  def testCalendar() {
    val totalDays = 10593
    val originYear = 1980

    val (year, day) = choose((year: Val[Int], day: Val[Int]) => {
      def leapDaysUntil(y: Tree[IntSort]) = (y - 1) / 4 - (y - 1) / 100 + (y - 1) / 400

      totalDays === (year - originYear) * 365 + leapDaysUntil(year) - leapDaysUntil(originYear) + day &&
        day > 0 && day <= 366
    })

    year should equal(2008)
    day should equal(366)
  }
}
