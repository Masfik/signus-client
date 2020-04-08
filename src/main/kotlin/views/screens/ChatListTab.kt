package views.screens

import models.AuthUserModel
import models.Chat
import tornadofx.View
import tornadofx.bindSelected
import tornadofx.listview
import views.components.ChatTile

class ChatListTab : View() {
  private val authUser: AuthUserModel by inject()

  override val root = listview<Chat>(authUser.chats.value) {
    cellFragment(ChatTile::class)
    bindSelected(authUser.activeChat)
  }
}