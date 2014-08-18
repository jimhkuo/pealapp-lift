package act

trait LeafType extends Act
case class AttLeaf(name : String, pred : String, prob: BigDecimal, cost: BigDecimal, impact: BigDecimal) extends LeafType
case class DetLeaf(name : String, pred : String, prob: BigDecimal, cost: BigDecimal) extends LeafType with DmAct
case class MitLeaf(name : String, pred : String, prob: BigDecimal, cost: BigDecimal) extends LeafType with DmAct

trait Act
case class ActLeaf(leafType : LeafType) extends Act
case class AndLeaf(name : String, lhs: Act, rhs: Act*) extends Act
case class OrLeaf(name : String, lhs: Act, rhs: Act*) extends Act
case class NotLeaf(name : String, dm: DmAct) extends Act

trait DmAct
case class AndDmAct(name: String, lhs: DmAct, rhs: DmAct*) extends DmAct
case class OrDmAct(name: String, lhs: DmAct, rhs: DmAct*) extends DmAct