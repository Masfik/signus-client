package views.screens

import javafx.geometry.Pos
import javafx.scene.input.KeyEvent
import models.AuthUserModel
import models.Chat
import tornadofx.*
import views.utils.MainStylesheet.Companion.chatScreen
import views.components.ChatTopBar
import views.components.MessageList
import views.components.MessageTile
import views.components.SendMessageBar

class ChatTab : View() {
  override val root = borderpane {
    setId(chatScreen)

    top<ChatTopBar>()
    center<MessageList>()
    bottom<SendMessageBar>()

    keyboard {
      addEventHandler(KeyEvent.KEY_PRESSED) { println(it.code) }
    }
  }
}
