package code
package lib

import net.liftweb._
import http._
import util._

object DependencyFactory extends Factory {

  implicit object time extends FactoryMaker(Helpers.now _)

  private def init() {
    List(time)
  }

  init()
}