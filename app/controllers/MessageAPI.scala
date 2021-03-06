package controllers

import akka.pattern.ask
import akka.util.Timeout

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import play.api.libs.iteratee.{Iteratee, Enumerator, Enumeratee}
import play.api.libs.concurrent.Execution.Implicits._

import net.fwbrasil.activate.play.EntityForm
import net.fwbrasil.activate.play.EntityForm._

import persistence.models._
import persistence.ChatPersistenceContext._
import actots._
import scala.concurrent.Future
import scala.concurrent.duration._

object MessageAPI extends Controller {

  val messageForm = EntityForm[Message](
    _.chat -> entity[Chat],
    _.body -> nonEmptyText
  )

  def channel = WebSocket.async[JsValue] { request =>
    implicit val timeout = Timeout(2 seconds)
    (ChatActors.broadcaster ? Listen).mapTo[Enumerator[JsValue]] map { out =>
      val in = Iteratee.foreach[JsValue] { message =>
        asyncTransactionalChain { implicit ctx =>
          messageForm.bind(message).fold(
            formWithErrors => Future.successful(
              BadRequest(
                Json.obj(
                  "code" -> "400",
                  "errors" -> formWithErrors.errorsAsJson
                ))),
            messageData => {
              messageData.put(_.created)(new java.util.Date())
              messageData.asyncCreateEntity map {message =>
                ChatActors.broadcaster ! Json.toJson(message)
              }
            }
          )
        }
      }
      (in, out)
    }
  }
}
