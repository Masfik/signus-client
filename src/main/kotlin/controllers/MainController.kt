package controllers

import com.tinder.scarlet.websocket.WebSocketEvent.*
import controllers.ChatTabController.Companion.scrollToBottom
import kotlinx.coroutines.flow.collect
import models.AuthUser
import models.AuthUserModel
import models.Chat
import models.User
import services.chat.updates.UserUpdateType.AUTH_USER
import services.chat.updates.UserUpdateType.USER
import tornadofx.*
import views.components.chattab.NoChatSelected
import views.components.chattab.SendMessageBar
import views.screens.ChatTab

class MainController : Controller() {
  private val authUser: AuthUserModel by inject()
  private var isChatOpen = false

  // Chat service
  private val chatService: ChatServiceController by inject()

  // TODO: Temporary placeholder data
  init {
    val authUser = AuthUser("Masfik", "Masfik", "email@email.com", 0)
    val tom = User("Tom", "Caedan", "tom", 1)
    val tomChat = Chat(tom)

    authUser.chats.addAll(
      tomChat,
      Chat(User("Masfik", "Username", "email", 2))
    )

    this.authUser.item = authUser
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
          find<NoChatSelected>().replaceWith(
            ChatTab::class,
            ViewTransition.Slide(0.2.seconds, ViewTransition.Direction.LEFT)
          )
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
      is OnConnectionOpened -> println("Connected successfully to Signus server.")
      is OnConnectionFailed -> println("Connection error.")
      is OnConnectionClosed -> println("Closed connection successfully.")
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