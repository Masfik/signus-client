package views.components.chatlisttab

import controllers.ChatListController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import models.AuthUserModel
import models.Chat
import tornadofx.View
import tornadofx.bindSelected
import tornadofx.listview

class ChatList : View() {
  private val controller: ChatListController by inject()
  private val authUser: AuthUserModel by inject()

  override val root = listview<Chat>(authUser.chats) {
    cellFragment(ChatTile::class)
    bindSelected(authUser.activeChat)
  }

  init {
    GlobalScope.launch { controller.observeIncomingChat() }
  }
}
