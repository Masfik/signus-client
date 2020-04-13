package views.components.chatlisttab

import models.AuthUserModel
import models.Chat
import tornadofx.View
import tornadofx.bindSelected
import tornadofx.listview

class ChatList : View() {
  private val authUser: AuthUserModel by inject()

  override val root = listview<Chat>(authUser.chats) {
    cellFragment(ChatTile::class)
    bindSelected(authUser.activeChat)
  }
}
