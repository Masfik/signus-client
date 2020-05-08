package controllers

import javafx.beans.property.SimpleStringProperty
import models.AuthUserModel
import services.api.adapters.LoginDetails
import tornadofx.*
import views.screens.LoginScreen
import views.screens.MainScreen
import java.lang.IllegalArgumentException

class LoginController : Controller() {
  // AuthUser Model
  private val authUser: AuthUserModel by inject()

  // Status
  val errorProperty = SimpleStringProperty()
  private var error by errorProperty

  suspend fun login(username: String, password: String) {
    // Reset error property and API
    runLater { error = "" }
    try {
      val response = find<AuthServiceController>()
        .login(LoginDetails(username, password))

      runLater {
        if (response.user != null) {
          authUser.item = response.user
          find<LoginScreen>().replaceWith(MainScreen::class)
        } else error = response.message
      }
    } catch (e: IllegalArgumentException) {
      runLater { error = "Unreachable Endpoint" }
    }
  }
}