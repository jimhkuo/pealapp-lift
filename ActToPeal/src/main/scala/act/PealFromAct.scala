package act

//dyn1204-114:~/PealApp-lift/target jkuo$ scalac -cp PealApp-lift-assembly-3.2.6.jar PealFromAct.scala
//dyn1204-114:~/PealApp-lift/target jkuo$ scala -cp PealApp-lift-assembly-3.2.6.jar:. PealFromAct

object PealFromAct extends App {

  val m2 = MitLeaf("2", "secureRouter", 0.5, 35)
  val d2 = DetLeaf("2", "routerFirewallAlert", 0.7, 15)
  val and5 = AndDmAct("5", d2, m2)
  val not2 = NotLeaf("2", and5)
  val a2 = AttLeaf("2", "alterConfigurationViaCompromisedRouter", 0.4, 190, 275)
  val and2 = AndLeaf("2", a2, not2)
  val m1 = MitLeaf("1", "randomizeSequenceNumbers", 0.6, 30)
  val d1 = DetLeaf("1", "traceRouteCheck", 0.5, 10)
  val and4 = AndDmAct("4", d1, m1)
  val not1 = NotLeaf("1", and4)
  val m12 = MitLeaf("12", "MD5authentication", 0.5, 20)
  val d12 = DetLeaf("12", "TCPsequenceNumberCheck", 0.8, 10)
  val and7 = AndDmAct("7", d12, m12)
  val not3 = NotLeaf("3", and7)
  val a12 = AttLeaf("12", "TCPsequenceNumberAttack", 0.1, 150, 250)
  val and6 = AndLeaf("6", a12, not3)
  val a1123 = AttLeaf("1123", "keepAlive", 0.2, 100, 300)
  val a1122 = AttLeaf("1122", "open", 0.15, 70, 100)
  val a1121 = AttLeaf("1121", "notify", 0.1, 60, 130)
  val or3 = OrLeaf("3", a1121, a1122, a1123)
  val a111 = AttLeaf("111", "sendRSTmessageToTCPStack", 0.08, 50, 200)
  val or2 = OrLeaf("2", a111, or3)
  val and3 = AndLeaf("3", or2, and6)
  val and1 = AndLeaf("1", and3, not1)
  val bgp = OrLeaf("1", and1, and2)

  println(ActToPeal.execute(bgp))
}

