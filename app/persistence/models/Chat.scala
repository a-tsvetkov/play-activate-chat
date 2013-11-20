package persistence.models

import play.api.libs.json._
import net.fwbrasil.radon.transaction.TransactionalExecutionContext

import persistence.ChatPersistenceContext._


case class Chat(val name: String) extends Entity

object Chat {

  val chatReads = Json.reads[Chat]

  object chatWrites extends Writes[Chat] {
    def writes(chat: Chat): JsValue = JsObject(List(
      "id" -> JsString(chat.id),
      "name" -> JsString(chat.name)
    ))
  }

  implicit val chatFormat = Format(chatReads, chatWrites)

  def all(implicit ctx: TransactionalExecutionContext) = {
    asyncAll[Chat]
  }

  def get(id: String)(implicit ctx: TransactionalExecutionContext) = {
    asyncById[Chat](id)
  }
}
