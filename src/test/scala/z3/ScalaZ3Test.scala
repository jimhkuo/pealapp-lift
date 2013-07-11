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

    println(sol)


    if (sol.get == true) {
      model.delete
    }

    solver.reset() //This resets the solver!!

    val q2a = z3.mkBoolConst("q2a")

    solver.assertCnstr(z3.mkAnd(z3.mkOr(q1a, f), q2a))

    println(solver.checkAndGetModel()._1)


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
}
