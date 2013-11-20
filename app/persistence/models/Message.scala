package persistence.models

import net.fwbrasil.radon.transaction.TransactionalExecutionContext
import play.api.libs.json._

import persistence.ChatPersistenceContext._

case class Message(
  body: String,
  created: java.util.Date,
  chat: Chat
) extends Entity

object Message {
  implicit val chatFormat = Json.format[Message]

  def forChatId(chat_id: String)(implicit ctx: TransactionalExecutionContext) = {
    asyncQuery {
      (m: Message) => where(m.chat.id :== chat_id) select (m) orderBy(m.created desc)
    }
  }
}
