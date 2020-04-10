package views.screens

import tornadofx.View
import tornadofx.addStylesheet
import tornadofx.borderpane
import views.components.chattab.ChatTabTopBar
import views.components.chattab.MessageList
import views.components.chattab.SendMessageBar
import views.stylesheets.ChatTabStylesheet

class ChatTab : View() {
  override val root = borderpane {
    addStylesheet(ChatTabStylesheet::class)

    top<ChatTabTopBar>()
    center<MessageList>()
    bottom<SendMessageBar>()
  }
}
