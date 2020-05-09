package controllers

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TextField
import models.AuthUserModel
import models.Chat
import retrofit2.HttpException
import tornadofx.Controller
import tornadofx.getValue
import tornadofx.runLater
import tornadofx.setValue

class AddChatController : Controller() {
  private val authUser: AuthUserModel by inject()

  // Show if the there is any user in the database
  val userFoundProperty = SimpleStringProperty("No user found.")
  private var userFound: String by userFoundProperty

  suspend fun search(textField: TextField) {
    if (textField.text.isEmpty()) return

    runLater { userFound = "Searching..." }

    try {
      val user = find<UserApiController>()
        .userByUsername(authUser.token.valueSafe, textField.text)

      if (user !== null) when {
        authUser.id.value == user.id -> runLater {
          userFound = "You cannot add yourself!"
        }
        authUser.chats.find { it.recipient.id == user.id } != null -> runLater {
          userFound = "This user is already part of your chats."
        }
        else -> runLater {
          authUser.chats.add(Chat(user, user.id))
          userFound = "User added!"
        }
      }
    } catch (e: HttpException) {
      runLater { userFound = "No user found." }
    }

    textField.text = ""
  }
}
