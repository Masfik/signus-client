package controllers

import controllers.ChatTabController.Companion.scrollToBottom
import models.AuthUser
import models.AuthUserModel
import models.Chat
import models.User
import tornadofx.*
import views.components.chattab.NoChatSelected
import views.components.chattab.SendMessageBar
import views.screens.ChatTab

class MainController : Controller() {
  private val authUser: AuthUserModel by inject()
  private var isChatOpen = false

  // TODO: Temporary placeholder data
  init {
    val authUser = AuthUser(0, "Masfik", "Masfik", "email@email.com")
    val tom = User(1, "Tom", "Caedan", "tom")
    val tomChat = Chat(242, tom)

    authUser.chats.addAll(
      tomChat,
      Chat(2, User(2, "Masfik", "Username", "email"))
    )

    this.authUser.item = authUser
  }

  // TODO: Might switch to EventBuses in the future to reduce coupling
  fun listenActiveChat() {
    authUser.activeChat.onChange { chat ->
      if (chat == null) {
        find<ChatTab>().replaceWith(
          NoChatSelected::class,
          ViewTransition.Slide(0.2.seconds, ViewTransition.Direction.RIGHT)
        )

        isChatOpen = false
      } else if (!isChatOpen) {
        find<NoChatSelected>().replaceWith(
          ChatTab::class,
          ViewTransition.Slide(0.2.seconds, ViewTransition.Direction.LEFT)
        )

        isChatOpen = true
        scrollToBottom(authUser)
      } else scrollToBottom(authUser)

      // The SendMessageBar will always request focus when the active chat changes
      runLater { find<SendMessageBar>().message.requestFocus() }
    }
  }
}