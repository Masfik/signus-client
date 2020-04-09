package views.screens

import models.AuthUserModel
import models.Chat
import tornadofx.View
import tornadofx.bindSelected
import tornadofx.listview
import tornadofx.style
import views.components.ChatTile
import views.stylesheets.SignusColour

class ChatListTab : View() {
  private val authUser: AuthUserModel by inject()

  override val root = listview<Chat>(authUser.chats) {
    style {
      backgroundColor += SignusColour.PRIMARY.value
    }

    cellFragment(ChatTile::class)
    bindSelected(authUser.activeChat)
  }
}
