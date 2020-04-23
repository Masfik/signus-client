package controllers

import javafx.beans.property.SimpleStringProperty
import models.AuthUser
import models.AuthUserModel
import models.ServerSettingsModel
import services.authentication.AuthService
import services.authentication.SignusAuthService
import tornadofx.*
import tornadofx.Rest.Response
import views.screens.MainScreen
import views.screens.LoginScreen

class LoginController : Controller() {
  // Authentication service (this can be replaced in the future by some other auth provider)
  val authService: AuthService<AuthUser> = SignusAuthService
  // Models
  private val serverSettings: ServerSettingsModel by inject()
  private val authUser: AuthUserModel by inject()
  // Status
  val errorProperty = SimpleStringProperty()
  private var error by errorProperty
  // Rest API
  private val api: Rest by inject()

  fun login(username: String, password: String) {
    runLater { error = "" }
    api.setBasicAuth(username, password)

    val response: Response
    try {
      response = api.get("user")
      val json = response.one()

      runLater {
        if (response.ok()) {
          authUser.item = json.toModel()
          find<LoginScreen>().replaceWith(MainScreen::class, sizeToScene = true, centerOnScreen = true)
        } else error = json.string("message") ?: "Login failed"
      }
    } catch (e: RestException) {
      runLater {
        error = "Unreachable Endpoint"
        // TODO: change this
        find<LoginScreen>().replaceWith(MainScreen::class, centerOnScreen = true)
      }
    }
  }
}