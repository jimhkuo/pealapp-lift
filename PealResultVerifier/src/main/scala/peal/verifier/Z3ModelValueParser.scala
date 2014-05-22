package peal.verifier

import peal.domain.{PealFalse, PealTrue, ThreeWayBooleanObj, ThreeWayBoolean}


object Z3ModelValueParser {
  def parse(value: String): Either[BigDecimal, ThreeWayBoolean] = {
    ThreeWayBooleanObj.from(value) match {
      case PealTrue | PealFalse => Right(ThreeWayBooleanObj.from(value))
      case _ => Left(-1)
    }
  }
}
