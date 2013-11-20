package actots

import akka.actor._
import play.api.libs.iteratee.{Concurrent, Enumerator}
import play.api.libs.json._

case object Listen

object ChatActors {
  val system = ActorSystem("chat")
  lazy val broadcaster = system.actorOf(Props[ChatActor], "ChatActor")
}

class ChatActor extends Actor with ActorLogging{

  val (out, channel) = Concurrent.broadcast[JsValue]

  def receive = {
    case message: JsValue => {
      channel.push(message)
    }
    case Listen => {
      sender ! out
    }
  }
}
