package peal.domain

trait ThreeWayBoolean {
  def unary_! = this match {
    case PealTrue => PealFalse
    case PealFalse => PealTrue
    case PealBottom => PealBottom
  }

  //intentionally spell all cases out
  def ||(obj: ThreeWayBoolean): ThreeWayBoolean = this match {
    case PealTrue => obj match {
      case PealTrue => PealTrue
      case PealFalse => PealTrue
      case PealBottom => PealTrue
    }
    case PealFalse => obj match {
      case PealTrue => PealTrue
      case PealFalse => PealFalse
      case PealBottom => PealBottom
    }
    case PealBottom => obj match {
      case PealTrue => PealTrue
      case PealFalse => PealBottom
      case PealBottom => PealBottom
    }
  }

  def &&(obj: ThreeWayBoolean): ThreeWayBoolean = this match {
    case PealTrue => obj match {
      case PealTrue => PealTrue
      case PealFalse => PealFalse
      case PealBottom => PealBottom
    }
    case PealFalse => obj match {
      case PealTrue => PealFalse
      case PealFalse => PealFalse
      case PealBottom => PealFalse
    }
    case PealBottom => PealBottom
  }

  def ===(obj: ThreeWayBoolean): ThreeWayBoolean = this match {
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
  def from(b: Boolean): ThreeWayBoolean = b match {
    case true => PealTrue
    case false => PealFalse
  }

  def from(s: String): ThreeWayBoolean = s match {
    case "true" => PealTrue
    case "false" => PealFalse
    case _ => PealBottom
  }
}

object PealTrue extends ThreeWayBoolean {
  override def toString = "True"
}

object PealFalse extends ThreeWayBoolean {
  override def toString = "False"
}

object PealBottom extends ThreeWayBoolean {
  override def toString = "Bottom"
}