package views.screens

import tornadofx.View
import tornadofx.borderpane
import tornadofx.setId
import views.components.ChatTopBar
import views.components.MessageList
import views.components.SendMessageBar
import views.utils.MainStylesheet.Companion.chatScreen

class ChatTab : View() {
  override val root = borderpane {
    setId(chatScreen)

    top<ChatTopBar>()
    center<MessageList>()
    bottom<SendMessageBar>()
  }
}
