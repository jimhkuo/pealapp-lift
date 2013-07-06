package z3

import org.junit.{Ignore, Test}
import org.scalatest.junit.ShouldMatchersForJUnit
import z3.scala.dsl._
import z3.scala._

class ScalaZ3Test extends ShouldMatchersForJUnit {


  @Test
  def testMxingContext() {
    var z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val q1a = z3.mkBoolConst("q1")
    val f = z3.mkFalse()
    val solver = z3.mkSolver
    solver.assertCnstr(z3.mkAnd(q1a, f))

    var (sol, model) = solver.checkAndGetModel

    sol should be (Some(false))

    if (sol.get == true) {
      model.delete
    }

    val q1 = z3.mkBoolConst("q1")
    val q2 = z3.mkBoolConst("q2")
    val q1Orq2 = z3.mkOr(q1, q2)
    val formula = z3.mkAnd(q1Orq2, q1)
    println(formula)
    //function that takes no arguments is a constant
    val cond = z3.mkBoolConst("cond")
    val equal = z3.mkEq(cond, formula)
    println(equal)
    val solver1 = z3.mkSolver
    solver1.assertCnstr(equal)
    solver1.assertCnstr(z3.mkNot(cond))

    var (sol1, model1) = solver1.checkAndGetModel

    sol1 should be(Some(true))
    model1.evalAs[Boolean](q1) should be(Some(false))

    if (sol1.get == true) {
      model1.delete
    }

    z3.delete()
    z3 = null
  }


  @Test
  def testFunction2() {
    //    (declare-const x Real)
    //    (declare-const y Real)
    //    (declare-const q Bool)
    //    (assert (= q (> x y)))
    //    (assert (and q (= x (* x x))))
    //    (check-sat)
    //    (get-model)
    var z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val x = z3.mkIntConst("x")
    val y = z3.mkIntConst("y")
    val q = z3.mkBoolConst("q")
    val solver = z3.mkSolver
    solver.assertCnstr(z3.mkEq(q, z3.mkGT(x, y)))
    solver.assertCnstr(z3.mkAnd(q, z3.mkEq(x, z3.mkMul(x, x))))

    var (sol, model) = solver.checkAndGetModel
    sol should be(Some(true))
    model.evalAs[Boolean](q) should be(Some(true))
    model.evalAs[Int](x) should be(Some(0))
    model.evalAs[Int](y) should be(Some(-1))


    model.delete
    z3.delete()
    z3 = null
  }

  @Test
  def testFunction() {
    //  (declare-const q1 Bool)
    //  (declare-const q2 Bool)
    //  (declare-fun cond () Bool)
    //  (assert (= cond (and (or q1 q2) q1)))
    //  (assert (not cond))
    //  (check-sat)
    var z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val q1 = z3.mkBoolConst("q1")
    val q2 = z3.mkBoolConst("q2")
    val q1Orq2 = z3.mkOr(q1, q2)
    val formula = z3.mkAnd(q1Orq2, q1)
    println(formula)
    //function that takes no arguments is a constant
    val cond = z3.mkBoolConst("cond")
    val equal = z3.mkEq(cond, formula)
    println(equal)
    val solver = z3.mkSolver
    solver.assertCnstr(equal)
    solver.assertCnstr(z3.mkNot(cond))

    var (sol, model) = solver.checkAndGetModel

    sol should be(Some(true))
    model.evalAs[Boolean](q1) should be(Some(false))

    model.delete
    z3.delete()
    z3 = null
  }

  @Test
  def testUnsatModel() {
    var z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val q1 = z3.mkBoolConst("q1")
    val f = z3.mkFalse()
    val solver = z3.mkSolver
    solver.assertCnstr(z3.mkAnd(q1, f))

    var (sol, model) = solver.checkAndGetModel

    sol should equal(Some(false))
    z3.delete()
    z3 = null
  }

  @Test
  def testASimplePealModel() {
    //  (declare-const q1 Bool)
    //  (declare-const q2 Bool)
    //  (assert (and (or q1 q2) q1))
    //  (check-sat)
    //  (get-model)

    var z3 = new Z3Context(new Z3Config("MODEL" -> true))
    val q1 = z3.mkBoolConst("q1")
    val q2 = z3.mkBoolConst("q2")
    val and = z3.mkAnd(z3.mkOr(q1, q2), q1)
    val solver = z3.mkSolver
    solver.assertCnstr(and)

    var (sol, model) = solver.checkAndGetModel

    sol should equal(Some(true))
    model.evalAs[Boolean](q1) should be(Some(true))
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
