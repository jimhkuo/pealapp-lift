package peal.maximise

object Main extends App {
  val input = scala.io.Source.fromFile(args(0)).mkString
  val out = args.length match {
    case 3 => MaximisePSet(input, args(1), BigDecimal(args(2))).doIt()
    case 4 => MaximisePSet(input, args(1), BigDecimal(args(2)), args(3)).doIt()
    case _ => "Arguments: peal_file_name pSet accuracy [pol]"
  }

  println(out)
}
