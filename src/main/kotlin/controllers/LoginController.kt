package controllers

import javafx.beans.property.SimpleStringProperty
import models.UserModel
import tornadofx.*
import views.screens.MainScreen
import views.screens.LoginScreen

class LoginController : Controller() {
  val statusProperty = SimpleStringProperty("")
  private var status by statusProperty
  private val user: UserModel by inject()

  private val api: Rest by inject()

  init {
    api.baseURI = "localhost"
  }

  fun login(username: String, password: String) {
    runLater { status = "" }
    api.setBasicAuth(username, password)
    val response = api.get("user")
    val json = response.one()
    runLater {
      if (response.ok()) {
        user.item = json.toModel()
        find(LoginScreen::class).replaceWith(MainScreen::class, sizeToScene = true, centerOnScreen = true)
      } else status = json.string("message") ?: "Login failed"
    }
  }
}