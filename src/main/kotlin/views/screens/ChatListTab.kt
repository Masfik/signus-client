package views.screens

import controllers.ChatListController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.*
import views.components.chatlisttab.ChatList
import views.components.chatlisttab.ChatListTopBar

class ChatListTab : View() {
  private val controller: ChatListController by inject()

  init {
    GlobalScope.launch { controller.observeIncomingChat() }
  }

  override val root = borderpane {
    top<ChatListTopBar>()
    center<ChatList>()
  }
}
