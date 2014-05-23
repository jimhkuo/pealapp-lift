package peal.verifier

import peal.domain.{PealFalse, PealTrue, ThreeWayBooleanObj, ThreeWayBoolean}
import peal.verifier.util.Rational


object Z3ModelValueParser {
  val rational = """([\d.]+)/([\d.]+)""".r

  def parse(value: String): Either[BigDecimal, ThreeWayBoolean] = {
    ThreeWayBooleanObj.from(value) match {
      case PealTrue | PealFalse => Right(ThreeWayBooleanObj.from(value))
      case _ => Left(convertToBigDecimal(value))
    }
  }

  private def convertToBigDecimal(v : String) = {
    try {
      BigDecimal.valueOf(v.toDouble)
    } catch {
      case e: NumberFormatException =>
        val rational(numer, denom) = v
        new Rational(numer, denom).value

    }
  }
}
