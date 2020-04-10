package views.screens

import tornadofx.*
import views.components.chatlisttab.ChatList
import views.components.chatlisttab.ChatListTopBar

class ChatListTab : View() {
  override val root = borderpane {
    top<ChatListTopBar>()
    center<ChatList>()
  }
}
