package controllers

import javafx.scene.control.TextField
import models.AuthUserModel
import models.Chat
import models.Message
import tornadofx.*
import views.components.chattab.MessageList

class ChatTabController : Controller() {
  private val authUser: AuthUserModel by inject()

  companion object {
    fun scrollToBottom(authUser: AuthUserModel) = find<MessageList>().root.scrollTo(
      authUser.activeChat.select(Chat::messagesProperty).value.lastIndex
    )
  }

  fun submit(textField: TextField) {
    if (textField.text.isEmpty()) return

    val messages = authUser.activeChat.select(Chat::messagesProperty).value
    messages.add(Message(textField.text, authUser.item))

    textField.text = ""
    scrollToBottom(authUser)
  }
}