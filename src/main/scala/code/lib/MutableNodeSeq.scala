package code.lib

import scala.xml.{Node, NodeSeq}

trait NodeAppender {
  def appendNode(node: Node): NodeSeq => NodeSeq
}

object MutableNodeSeq {
  def apply(nodes: NodeSeq) = new MutableNodeSeq(nodes)
  def apply() = new MutableNodeSeq(NodeSeq.Empty)
}

class MutableNodeSeq(var nodes: NodeSeq) {
  implicit object NodeAppenderObj extends NodeAppender {
    override def appendNode(node: Node) = (nodes: NodeSeq) => {
      nodes :+ node
    }
  }

  implicit def fops(ns: NodeSeq) = new {
    val witness = implicitly[NodeAppender]

    final def append(node: Node): NodeSeq = {
      witness.appendNode(node)(ns)
    }
  }

  def append(moreNodes: NodeSeq) {
    nodes = nodes ++ moreNodes
  }

  def append(node: Node) {
    nodes = nodes.append(node)
  }

  def append(s: String) {
    append(s.split("\n").toList)
  }

  def append(lines: List[String]) {
    lines.foreach(l => nodes = nodes.append(<span>
      {l}<br/>
    </span>))

  }

  override def toString = nodes.toString()
}
