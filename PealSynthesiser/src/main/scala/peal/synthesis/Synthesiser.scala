package peal.synthesis

trait Synthesiser {
  def generate(doVacuityCheck : Boolean = false) : String
}