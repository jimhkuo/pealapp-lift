package peal.synthesis

trait Synthesiser {
  //TODO add doVacuityCheck parameter here with default to false
  def generate() : String
}