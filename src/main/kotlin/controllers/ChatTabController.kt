package controllers

import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import models.AuthUserModel
import models.Chat
import models.Message
import models.UserStatus
import models.UserStatus.*
import services.chat.updates.MessageUpdate
import tornadofx.*
import views.components.chattab.ChatTabTopBar
import views.components.chattab.ChatTabTopBar.Companion.statusCircle
import views.components.chattab.ChatTabTopBar.Companion.statusText
import views.components.chattab.MessageList
import views.stylesheets.SignusTheme

class ChatTabController : Controller() {
  private val chatService: ChatServiceController by inject()
  private val authUser: AuthUserModel by inject()

  init {
    authUser.activeChat.onChange { chat ->
      chat?.recipient?.statusProperty?.onChange(this::changeStatus)
    }
  }

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

  private fun changeStatus(status: UserStatus?) {
    val topBar = find<ChatTabTopBar>().root

    when (status) {
      ONLINE -> {
        topBar.select<Circle>(statusCircle).fill = SignusTheme.GREEN
        topBar.select<Label>(statusText).text = status.name
      }
      OFFLINE -> {
        topBar.select<Circle>(statusCircle).fill = Color.GRAY
        topBar.select<Label>(statusText).text = status.name
      }
      BUSY -> {
        topBar.select<Circle>(statusCircle).fill = SignusTheme.RED
        topBar.select<Label>(statusText).text = status.name
      }
    }
  }
}