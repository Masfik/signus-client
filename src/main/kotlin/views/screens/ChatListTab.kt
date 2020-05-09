package views.screens

import tornadofx.*
import views.components.chatlisttab.ChatList
import views.components.chatlisttab.ChatListTopBar
import views.stylesheets.SignusTheme

class ChatListTab : View() {
  override val root = borderpane {
    style { backgroundColor += SignusTheme.PRIMARY }

    top<ChatListTopBar>()
    center<ChatList>()
  }
}
