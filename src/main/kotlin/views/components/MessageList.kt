package views.components

import misc.ChatSelectionModel
import models.AuthUserModel
import models.Chat
import models.Message
import tornadofx.*

class MessageList : View("My View") {
  private val authUser: AuthUserModel by inject()
  private var messageList = authUser.activeChat.select(Chat::messagesProperty)

  override val root = listview<Message>(messageList) {
    cellFragment(MessageTile::class)
    selectionModel = ChatSelectionModel()
  }
}
