import java.util
trait Functor[F[_]] {
  def map[X, Y](f: X => Y): F[X] => F[Y]
}


implicit object JAL_a_Functor extends Functor[util.ArrayList] {
  def map[X, Y](f: X => Y) = (xs: util.ArrayList[X]) => {
    val ys = new util.ArrayList[Y]
    for (i <- 0 until xs.size) ys.add(f(xs.get(i)))
    ys
  }
}


implicit def fops[F[_] : Functor, A](fa: F[A]) = new {
  val witness = implicitly[Functor[F]]

  final def map[B](f: A => B): F[B] = witness.map(f)(fa)
}

val testList = new util.ArrayList(util.Arrays.asList("this", "is", "a", "test"))
testList.map(_.toUpperCase)