package controllers

import kotlinx.coroutines.flow.collect
import models.AuthUserModel
import models.Chat
import models.Message
import tornadofx.Controller
import tornadofx.runLater

class ChatListController : Controller() {
  private val chatService: ChatServiceController by inject()
  private val authUser: AuthUserModel by inject()
  private val userAPI: UserApiController by inject()

  suspend fun observeIncomingMessage() = chatService.observeIncomingMessage().collect { update ->
    val chat = authUser.chats.find { it.id == update.chatId }

    if (chat !== null) runLater {
      chat.messageList.add(Message(update.data, chat.recipient, update.dateTime))
    } else {
      val user = userAPI.userById(authUser.token.valueSafe, update.chatId)
      if (user !== null) runLater {
        authUser.chats.add(
          Chat(
            user,
            user.id,
            listOf(Message(update.data, user, update.dateTime))
          )
        )
      }
    }

    // TODO: simple notification
    /*runLater {
      val avatar = ImageView("user.png")
      avatar.fitHeight = 50.0
      avatar.fitWidth = avatar.fitHeight

      Notifications.create()
        .graphic(avatar)
        .title(chat?.recipient?.fullName ?: user?.fullName)
        .text(update.data)
        .owner(owner)
        .darkStyle()
        .position(Pos.BOTTOM_LEFT)
        .show()
    }*/
  }
}