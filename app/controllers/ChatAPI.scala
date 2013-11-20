package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.data._
import play.api.data.Forms._

import net.fwbrasil.activate.play.EntityForm
import net.fwbrasil.activate.play.EntityForm._

import persistence.models._
import persistence.ChatPersistenceContext._
import scala.concurrent.Future

object ChatAPI extends Controller {

  val chatForm = EntityForm[Chat](
    _.name -> nonEmptyText
  )

  def list = Action.async { implicit request =>
    asyncTransactionalChain { implicit ctx =>
      Chat.all.map {
        chats => Ok(
          Json.obj(
            "total" -> chats.length,
            "items" -> chats
          )
        )
      }
    }
  }

  def get(id:String) = Action.async { implicit request =>
    asyncTransactionalChain { implicit ctx =>
      for{
        chat <- Chat.get(id)
        messages <- Message.forChatId(id)
      } yield chat match {
        case Some(chat) => Ok(
          Json.obj(
            "chat" -> chat,
            "messages" -> messages
          )
        )
        case _ => NotFound(
          Json.obj(
            "code" -> "404",
            "message" -> Json.toJson("Chat with id = " + id + " not found")
          )
        )
      }
    }
  }

  def create = Action.async(parse.json) { implicit request =>
    asyncTransactionalChain { implicit ctx =>
      chatForm.bind(request.body).fold(
        formWithErrors => Future.successful(
          BadRequest(
            Json.obj(
              "code" -> "400",
              "errors" -> formWithErrors.errorsAsJson
            )
          )
        ),
        chatData => chatData.asyncCreateEntity map {chat =>
          Ok("Success")
        }
      )
    }
  }
}
