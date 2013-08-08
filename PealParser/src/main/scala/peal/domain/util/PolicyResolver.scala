package peal.domain.util

import peal.domain.{PolicySet, BasicPolicySet, Pol}
import java.util.Map
import scala.collection.JavaConversions._


object PolicyResolver {
  def getFromOr(polMap: Map[String, Pol], pSetMap: Map[String, PolicySet], name: String) : PolicySet = {
    if (polMap.contains(name)) {
      new BasicPolicySet(polMap(name))
    }
    else {
      pSetMap(name)
    }
  }

}
