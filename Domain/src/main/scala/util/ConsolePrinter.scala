package util

object ConsolePrinter {

  var loggingFunction = (s: String) => {}

  def enable() {
    loggingFunction = (s: String) => println(s)
  }

  def log(message: String) {
    loggingFunction(message)
  }
}