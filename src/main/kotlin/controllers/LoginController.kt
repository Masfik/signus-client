package controllers

import javafx.beans.property.SimpleStringProperty
import models.AuthUserModel
import models.ServerSettingsModel
import tornadofx.*
import tornadofx.Rest.Response
import views.screens.MainScreen
import views.screens.LoginScreen

class LoginController : Controller() {
  // Models
  private val serverSettings: ServerSettingsModel by inject()
  private val authUser: AuthUserModel by inject()

  // Status
  val errorProperty = SimpleStringProperty()
  private var error by errorProperty

  // Rest API
  private val api: Rest by inject()

  fun login(username: String, password: String) {
    // Reset error property
    runLater { error = "" }
    api.setBasicAuth(username, password)

    val response: Response
    try {
      response = api.get("user")
      val json = response.one()

      runLater {
        if (response.ok()) {
          authUser.item = json.toModel()
          find<LoginScreen>().replaceWith(MainScreen::class)
        } else error = json.string("message") ?: "Login failed"
      }
    } catch (e: RestException) {
      runLater {
        error = "Unreachable Endpoint"
        // TODO: change this
        find<LoginScreen>().replaceWith(MainScreen::class)
      }
    }
  }
}