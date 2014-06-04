def concat[T](xs: List[T], ys: List[T]): List[T] = (xs foldRight ys)(_ :: _)

val list1 = List(1, 2, 3, 4)
val list10 = List(10, 20, 30, 40)

val p = (list1 foldRight list10)(_ :: _)
val p1 = list1.foldRight(list10.sum)(_ + _)
concat(list1, list10)


//worksheet doesn't like var