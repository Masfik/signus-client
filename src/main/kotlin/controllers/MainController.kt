package controllers

import controllers.ChatTabController.Companion.scrollToBottom
import models.AuthUserModel
import tornadofx.Controller
import tornadofx.ViewTransition
import tornadofx.onChange
import tornadofx.seconds
import views.components.NoChatSelected
import views.screens.ChatTab

class MainController : Controller() {
  private val authUser: AuthUserModel by inject()
  private var isChatOpen = false

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
    }
  }
}