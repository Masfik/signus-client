package controllers

import kotlinx.coroutines.flow.collect
import models.AuthUserModel
import tornadofx.Controller

class ChatListController : Controller() {
  private val chatService: ChatServiceController by inject()
  private val authUser: AuthUserModel by inject()

  suspend fun observeIncomingChat() = chatService.observeIncomingChat().collect {
    authUser.chats.value.add(it)
  }
}