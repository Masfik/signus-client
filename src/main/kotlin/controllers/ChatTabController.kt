package controllers

import javafx.scene.control.TextField
import kotlinx.coroutines.flow.collect
import models.AuthUserModel
import models.Chat
import models.Message
import services.chat.updates.MessageUpdate
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

    val msg = Message(textField.text, authUser.item)
    val messageList = authUser.activeChat.select(Chat::messagesProperty).value
    messageList.add(msg)

    chatService.sendMessage(
      MessageUpdate(
        authUser.activeChat.value.id ?: "-1",
        msg.data as String,
        msg.dateTime
      )
    )

    textField.text = ""
    scrollToBottom(authUser)
  }

  suspend fun observeIncomingMessage() = chatService.observeIncomingMessage().collect { update ->
    val chat = authUser.chats.value.find { it.id == update.chatId }

    chat?.messageList?.add(with(update) {
      Message(data, chat.recipient, messageId, dateTime)
    })
  }
}