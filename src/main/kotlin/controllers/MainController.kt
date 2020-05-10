package controllers

import com.tinder.scarlet.websocket.WebSocketEvent.*
import controllers.ChatTabController.Companion.scrollToBottom
import kotlinx.coroutines.flow.collect
import models.*
import services.chat.updates.UserUpdateType.AUTH_USER
import services.chat.updates.UserUpdateType.USER
import tornadofx.*
import views.components.chatlisttab.ChatList
import views.components.chattab.NoChatSelected
import views.components.chattab.SendMessageBar
import views.screens.ChatTab

class MainController : Controller() {
  private val authUser: AuthUserModel by inject()
  private var isChatOpen = false

  // Chat service
  private val chatService: ChatServiceController by inject()

  init {
    val sampleUser = User(
      id        = authUser.id.value,
      firstName = "Sample",
      lastName  = "Chat",
      username  = "SampleChat",
      email     = "sample@chat.com"
    )

    authUser.chats.add(
      Chat(
        sampleUser,
        sampleUser.id,
        listOf(
          Message("You won't receive any messages here.", sampleUser),
          Message("Feel free to type as much as you want.", sampleUser),
          Message("This is a sample chat.", sampleUser)
        )
      )
    )
  }

  // TODO: Might switch to EventBuses in the future to reduce coupling
  fun listenActiveChat() {
    authUser.activeChat.onChange { chat ->
      when {
        chat == null -> {
          find<ChatTab>().replaceWith(NoChatSelected::class)
          isChatOpen = false
        }
        isChatOpen.not() -> {
          find<NoChatSelected>().replaceWith(ChatTab::class)
          isChatOpen = true
          scrollToBottom(authUser)
        }
        else -> scrollToBottom(authUser)
      }

      // The SendMessageBar will always request focus when the active chat changes
      runLater { find<SendMessageBar>().message.requestFocus() }
    }
  }

  suspend fun observeServerConnection() = chatService.observeWebSocketEvent().collect {
    when (it) {
      is OnConnectionOpened   -> println("Connected successfully to Signus server.")
      is OnConnectionClosed   -> println("Connection closed.")
      is OnConnectionFailed   -> println("Connection failed! Retrying...")
      is OnMessageReceived    -> println("New message received.")
      is OnConnectionClosing  -> println("Connection closing...")
    }
  }

  suspend fun observeUser() = chatService.observeUser().collect { update ->
    val user = update.user

    when (update.type) {
      AUTH_USER -> authUser.item?.update(user)
      USER -> authUser.chats.value.find { it.recipient.id == user.id }
        ?.recipient?.update(user)
    }
  }
}