package peal.domain

trait ThreeWayBoolean {
  def unary_! = this match {
    case PealTrue => PealFalse
    case PealFalse => PealTrue
    case PealBottom => PealBottom
  }

  def ||(obj: ThreeWayBoolean) : ThreeWayBoolean = this match {
    case PealTrue => obj match {
      case PealTrue => PealTrue
      case PealFalse => PealTrue
      case PealBottom => PealBottom
    }
    case PealFalse => obj match {
      case PealTrue => PealTrue
      case PealFalse => PealFalse
      case PealBottom => PealBottom
    }
    case PealBottom => PealBottom
  }

  def &&(obj: ThreeWayBoolean) : ThreeWayBoolean = this match {
    case PealTrue => obj match {
      case PealTrue => PealTrue
      case PealFalse => PealFalse
      case PealBottom => PealBottom
    }
    case PealFalse => obj match {
      case PealTrue => PealFalse
      case PealFalse => PealFalse
      case PealBottom => PealBottom
    }
    case PealBottom => PealBottom
  }

  def ===(obj:ThreeWayBoolean) : ThreeWayBoolean = this match {
    case PealTrue => obj match {
      case PealTrue => PealTrue
      case PealFalse => PealFalse
      case PealBottom => PealBottom
    }
    case PealFalse => obj match {
      case PealTrue => PealFalse
      case PealFalse => PealTrue
      case PealBottom => PealBottom
    }
    case PealBottom => PealBottom
  }
}


object ThreeWayBooleanObj {
  def from(b : Boolean) : ThreeWayBoolean = b match {
    case true => PealTrue
    case false => PealFalse
  }

  def from(s : String) : ThreeWayBoolean = s match {
    case "true" => PealTrue
    case "false" => PealFalse
    case _ => PealBottom
  }
}

object PealTrue extends ThreeWayBoolean
object PealFalse extends ThreeWayBoolean
object PealBottom extends ThreeWayBoolean