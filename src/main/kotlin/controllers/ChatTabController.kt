package controllers

import javafx.scene.control.TextField
import kotlinx.coroutines.flow.collect
import models.AuthUserModel
import models.Chat
import models.Message
import tornadofx.*
import views.components.chattab.MessageList

class ChatTabController : Controller() {
  private val chatService: ChatServiceController by inject()
  private val authUser: AuthUserModel by inject()

  companion object {
    fun scrollToBottom(authUser: AuthUserModel) = find<MessageList>().root.scrollTo(
      authUser.activeChat.select(Chat::messagesProperty).value.lastIndex
    )
  }

  fun submit(textField: TextField) {
    if (textField.text.isEmpty()) return

    val messages = authUser.activeChat.select(Chat::messagesProperty).value
    messages.add(Message(0, textField.text, authUser.item))

    textField.text = ""
    scrollToBottom(authUser)
  }

  suspend fun observeIncomingMessage() = chatService.observeIncomingMessage().collect { update ->
    authUser.chats.value.find { it.id == update.chatId }
      ?.messageList?.add(update.message)
  }
}