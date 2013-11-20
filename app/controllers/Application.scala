package controllers

import play.api._
import play.api.mvc._

import persistence.models._
import persistence.ChatPersistenceContext._

object Application extends Controller {

  def index = Action { implicit request =>
      Ok(views.html.index())
  }

  def jsRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
        Assets.at,
        ChatAPI.list,
        MessageAPI.channel
      )
    ).as("text/javascript")
  }
}
