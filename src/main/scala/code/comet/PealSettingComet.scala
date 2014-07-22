package code.comet

import code.lib._
import net.liftweb.http._

class PealSettingComet extends CometActor {
  def render: RenderOut = {
    this ! Init
    <div></div>
  }

  override def lowPriority: PartialFunction[Any, Unit] = {
    case Init =>
  }

}







