package peal.actor

import akka.actor.Actor
import peal.model.RandomModelGenerator

class ExperimentActor(params: String) extends Actor{
  def receive = {
    case s : String =>
      val result = RandomModelGenerator.generate(params.split(Array(' ', ',')).filterNot(_ == ""): _*)
      sender ! result
  }
}
