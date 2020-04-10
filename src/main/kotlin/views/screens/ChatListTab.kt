package views.screens

import models.AuthUserModel
import models.Chat
import tornadofx.*
import views.components.ChatTile
import views.stylesheets.ChatListStylesheet

class ChatListTab : View() {
  private val authUser: AuthUserModel by inject()

  override val root = listview<Chat>(authUser.chats) {
    addStylesheet(ChatListStylesheet::class)

    cellFragment(ChatTile::class)
    bindSelected(authUser.activeChat)
  }
}
