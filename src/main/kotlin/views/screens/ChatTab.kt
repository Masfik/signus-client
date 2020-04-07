package views.screens

import javafx.geometry.Pos
import javafx.scene.input.KeyEvent
import models.AuthUserModel
import models.Chat
import tornadofx.*
import views.MainStylesheet.Companion.chatScreen
import views.components.ChatTopBar
import views.components.Message
import views.components.SendMessageBar

class ChatTab : View() {
  private val authUser: AuthUserModel by inject()
  private val messageList = authUser.activeChat.select(Chat::messagesProperty)

  override val root = borderpane {
    setId(chatScreen)

    top<ChatTopBar>()
    center {
      vbox {
        alignment = Pos.BOTTOM_LEFT

        for (i in 0..5) this += find(Message::class)
      }
    }
    bottom<SendMessageBar>()

    keyboard {
      addEventHandler(KeyEvent.KEY_PRESSED) { println(it.code) }
    }
  }
}
