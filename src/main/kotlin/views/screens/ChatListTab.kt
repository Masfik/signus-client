package views.screens

import controllers.ChatListController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.*
import views.components.chatlisttab.ChatList
import views.components.chatlisttab.ChatListTopBar
import views.stylesheets.SignusTheme

class ChatListTab : View() {
  private val controller: ChatListController by inject()

  init {
    GlobalScope.launch(Dispatchers.IO) { controller.observeIncomingChat() }
  }

  override val root = borderpane {
    style { backgroundColor += SignusTheme.PRIMARY }

    top<ChatListTopBar>()
    center<ChatList>()
  }
}
