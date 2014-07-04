package peal.util

object ConsoleLogger {

  var loggingFunction = (s: Any, level: Int) => {}

  def enable() {
    enable(0)
  }

  def enable(l: Int) {
    loggingFunction = (s: Any, level: Int) => {
      if (level <= l)
        println(s)
    }
  }

  def log(message: Any) {
    loggingFunction(message, 0)
  }

  def log1(message: Any) {
    loggingFunction(message, 1)
  }

  def log2(message: Any) {
    loggingFunction(message, 2)
  }

}