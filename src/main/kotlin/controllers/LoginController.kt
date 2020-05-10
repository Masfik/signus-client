package controllers

import javafx.beans.property.SimpleStringProperty
import models.AuthUserModel
import models.ServerSettingsModel
import services.api.adapters.LoginDetails
import tornadofx.*
import views.screens.LoginScreen
import views.screens.MainScreen
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.net.ConnectException

class LoginController : Controller() {
  // AuthUser Model
  private val authUser: AuthUserModel by inject()
  private val serverSettings: ServerSettingsModel by inject()

  // Status
  val errorProperty = SimpleStringProperty()
  private var error by errorProperty

  init {
    serverSettings.baseEndpoint.value = "localhost:3000"
  }

  suspend fun login(username: String, password: String) {
    // Reset error property
    runLater { error = "" }

    try {
      val response = AuthApiController()
        .login(LoginDetails(username, password))

      runLater {
        if (response.user != null) {
          authUser.item = response.user
          find<LoginScreen>().replaceWith(MainScreen::class)
        } else error = response.message
      }
    } catch (e: Exception) {
      when (e) {
        is IllegalArgumentException,
        is ConnectException -> runLater { error = "Unreachable Endpoint" }
        else -> runLater { error = e.message }
      }
    }
  }
}