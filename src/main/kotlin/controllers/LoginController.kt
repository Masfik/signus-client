package controllers

import javafx.beans.property.SimpleStringProperty
import models.UserModel
import tornadofx.*
import tornadofx.Rest.Response
import views.screens.MainScreen
import views.screens.LoginScreen

class LoginController : Controller() {
  val statusProperty = SimpleStringProperty("")
  private var status by statusProperty

  val endpointProperty = SimpleStringProperty("https://")
  private var endpoint by statusProperty

  private val user: UserModel by inject()
  private val api: Rest by inject()

  init {
    api.baseURI = endpoint
  }

  fun login(username: String, password: String) {
    runLater { status = "" }
    api.setBasicAuth(username, password)

    val response: Response
    try {
      response = api.get("user")
      val json = response.one()

      runLater {
        if (response.ok()) {
          user.item = json.toModel()
          find<LoginScreen>().replaceWith(MainScreen::class, sizeToScene = true, centerOnScreen = true)
        } else status = json.string("message") ?: "Login failed"
      }
    } catch (e: RestException) {
      runLater {
        // status = "Unreachable Endpoint"
        // TODO: change this
        find<LoginScreen>().replaceWith(MainScreen::class, centerOnScreen = true)
      }
    }
  }
}