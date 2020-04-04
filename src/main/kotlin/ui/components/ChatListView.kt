package ui.components

import models.AuthUserModel
import models.Chat
import models.User
import tornadofx.*

class ChatListView : View() {
  private val authUser: AuthUserModel by inject()
  private val chats = authUser.chats.value

  override val root = listview(chats) {
    cellFragment(ChatTile::class)
  }
}
