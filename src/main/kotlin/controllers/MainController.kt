package controllers

import controllers.ChatTabController.Companion.scrollToBottom
import javafx.application.Platform
import models.AuthUserModel
import tornadofx.Controller
import tornadofx.ViewTransition
import tornadofx.onChange
import tornadofx.seconds
import views.components.chattab.NoChatSelected
import views.components.chattab.SendMessageBar
import views.screens.ChatTab

class MainController : Controller() {
  private val authUser: AuthUserModel by inject()
  private var isChatOpen = false

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
      Platform.runLater { find<SendMessageBar>().message.requestFocus() }
    }
  }
}