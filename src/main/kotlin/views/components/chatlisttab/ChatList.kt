package views.components.chatlisttab

import controllers.ChatListController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import models.AuthUserModel
import models.Chat
import tornadofx.View
import tornadofx.bindSelected
import tornadofx.listview

class ChatList : View() {
  private val authUser: AuthUserModel by inject()
  private val controller: ChatListController by inject()

  init {
    GlobalScope.launch(Dispatchers.IO) { controller.observeIncomingMessage() }
  }

  override val root = listview<Chat>(authUser.chats) {
    cellFragment(ChatTile::class)
    bindSelected(authUser.activeChat)
  }
}
