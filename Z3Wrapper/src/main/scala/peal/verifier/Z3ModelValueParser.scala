package peal.verifier

import peal.domain._

object Z3ModelValueParser {
  val rational = """([-\d.]+)/([\d.]+)""".r

  def parse(value: String): Either[BigDecimal, ThreeWayBoolean] = {
    ThreeWayBooleanObj.from(value) match {
      case PealTrue | PealFalse => Right(ThreeWayBooleanObj.from(value))
      case _ => Left(convertToBigDecimal(value))
    }
  }

  def parseToRational(value: String): Either[Rational, ThreeWayBoolean] = {
    ThreeWayBooleanObj.from(value) match {
      case PealTrue | PealFalse => Right(ThreeWayBooleanObj.from(value))
      case _ => Left(convertToRational(value))
    }
  }

  private def convertToBigDecimal(v : String) = {
    try {
      BigDecimal.valueOf(v.toDouble)
    } catch {
      case e: NumberFormatException =>
        val rational(numer, denom) = v
        Rational(numer, denom).value
    }
  }

  private def convertToRational(v : String) = {
    try {
      Rational(v)
    } catch {
      case e: NumberFormatException =>
        val rational(numer, denom) = v
        Rational(numer, denom)
    }
  }
}
