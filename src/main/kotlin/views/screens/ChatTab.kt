package views.screens

import controllers.ChatTabController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.View
import tornadofx.addStylesheet
import tornadofx.borderpane
import views.components.chattab.ChatTabTopBar
import views.components.chattab.MessageList
import views.components.chattab.SendMessageBar
import views.stylesheets.ChatTabStylesheet

class ChatTab : View() {
  private val controller: ChatTabController by inject()

  init {
    GlobalScope.launch(Dispatchers.IO) { controller.observeIncomingMessage() }
  }

  override val root = borderpane {
    addStylesheet(ChatTabStylesheet::class)

    top<ChatTabTopBar>()
    center<MessageList>()
    bottom<SendMessageBar>()
  }
}
