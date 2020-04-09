package views.screens

import tornadofx.View
import tornadofx.addStylesheet
import tornadofx.borderpane
import views.components.ChatTopBar
import views.components.MessageList
import views.components.SendMessageBar
import views.stylesheets.ChatTabStylesheet

class ChatTab : View() {
  override val root = borderpane {
    addStylesheet(ChatTabStylesheet::class)

    top<ChatTopBar>()
    center<MessageList>()
    bottom<SendMessageBar>()
  }
}
