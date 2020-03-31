package ui.components

import models.Chat
import models.User
import tornadofx.*

class ChatListView : View() {
  private val chats = listOf<Chat>(
    Chat(1, User("Masfik H", "Masfik", "contact@masfik.net")),
    Chat(2, User("Tom Neys", "Caedan", "neystom@hotmail.de"))
  ).asObservable()

  override val root = listview(chats) {
    cellFragment(ChatTile::class)
  }
}
