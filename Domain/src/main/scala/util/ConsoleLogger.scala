package util

object ConsoleLogger {

  var loggingFunction = (s: String, level: Int) => {}

  def enable() {
    enable(0)
  }

  def enable(l: Int) {
    loggingFunction = (s: String, level: Int) => {
      if (level <= l)
        println(s)
    }
  }

  def log(message: String) {
    loggingFunction(message, 0)
  }

  def log1(message: String) {
    loggingFunction(message, 1)
  }

  def log2(message: String) {
    loggingFunction(message, 2)
  }

}