package controllers

import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.receiveAsFlow
import models.AuthUserModel
import models.ServerSettingsModel
import okhttp3.OkHttpClient
import services.chat.ChatService
import tornadofx.Controller

class ChatServiceController : Controller() {
  private val serverSettings: ServerSettingsModel by inject()
  private val prefix = if (serverSettings.useHttps.value) "wss://" else "ws://"
  private val endpoint = prefix + serverSettings.baseEndpoint.value

  private val authUser: AuthUserModel by inject()

  private val scarletInstance = Scarlet.Builder()
    .webSocketFactory(OkHttpClient().newWebSocketFactory(endpoint))
    // TODO: add a message adapter factory
    .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
    .build()

  val service = scarletInstance.create<ChatService>()

  @ExperimentalCoroutinesApi
  suspend fun start() {
    service.observeWebSocketEvent().receiveAsFlow()
      .filter { it is WebSocket.Event.OnConnectionOpened<*> }
      .collect {
        println("Connected successfully.")
      }

    service.observeChats().receiveAsFlow()
      .collect { authUser.chats.value.add(it) }

    service.observeUsers().receiveAsFlow()
      .collect { user ->
        authUser.chats.value.find { it.recipient.id == user.id }
          ?.recipient?.update(user)
      }

    service.observeMessages().receiveAsFlow()
      .collect { update ->
        authUser.chats.value.find { it.id == update.chatId }
          ?.messageList?.add(update.message)
      }
  }
}
