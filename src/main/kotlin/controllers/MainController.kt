package controllers

import models.AuthUserModel
import tornadofx.*
import views.components.NoChatSelected
import views.screens.ChatTab

class MainController : Controller() {
  private var isChatOpen = false

  fun listenActiveChat() {
    find<AuthUserModel>().activeChat.onChange { chat ->
      if (chat == null) {
        find(ChatTab::class).replaceWith(
          NoChatSelected::class,
          ViewTransition.Slide(0.2.seconds, ViewTransition.Direction.RIGHT)
        )
        isChatOpen = false
      } else if (!isChatOpen) {
        find(NoChatSelected::class).replaceWith(
          ChatTab::class,
          ViewTransition.Slide(0.2.seconds, ViewTransition.Direction.LEFT)
        )
        isChatOpen = true
      }
    }
  }
}