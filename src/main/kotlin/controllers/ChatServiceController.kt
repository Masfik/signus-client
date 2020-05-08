package controllers

import com.squareup.moshi.Moshi
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.retry.ExponentialBackoffStrategy
import com.tinder.scarlet.websocket.ShutdownReason
import com.tinder.scarlet.websocket.WebSocketEvent
import com.tinder.scarlet.websocket.okhttp.OkHttpWebSocket
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import kotlinx.coroutines.flow.Flow
import models.AuthUserModel
import models.Chat
import models.ServerSettingsModel
import models.adapters.ChatAdapter
import models.adapters.MessageAdapter
import models.adapters.UserAdapter
import okhttp3.OkHttpClient
import okhttp3.Request
import services.SignusAppLifecycle
import services.chat.ChatService
import services.chat.updates.MessageUpdate
import services.chat.updates.MessageUpdateAdapter
import services.chat.updates.UserStatusUpdate
import services.chat.updates.UserUpdate
import tornadofx.Controller

class ChatServiceController : ChatService, Controller() {
  // Server settings
  private val serverSettings: ServerSettingsModel by inject()
  private val prefix = if (serverSettings.useHttps.value) "wss://" else "ws://"
  private val endpoint = prefix + serverSettings.baseEndpoint.value
  // AuthUser token
  private val token = find<AuthUserModel>().token.valueSafe

  // Moshi (JSON encoding<->decoding)
  private val moshi = Moshi.Builder()
    .add(UserAdapter())
    .add(ChatAdapter())
    .add(MessageAdapter())
    .add(MessageUpdateAdapter())
    .build()

  // Scarlet service
  private val scarletInstance = Scarlet(
    // Specifying which websocket adapter to use (OkHttp WebSocket)
    OkHttpWebSocket(
      OkHttpClient(),
      OkHttpWebSocket.SimpleRequestFactory(
        { Request.Builder().url(endpoint).header("Authorization", token).build() },
        { ShutdownReason.GRACEFUL }
      )
    ),
    // Scarlet configuration
    Scarlet.Configuration(
      lifecycle               = SignusAppLifecycle(),
      backoffStrategy         = ExponentialBackoffStrategy(1000, 60000),
      streamAdapterFactories  = listOf(CoroutinesStreamAdapterFactory()),
      messageAdapterFactories = listOf(MoshiMessageAdapter.Factory(moshi))
    )
  )
  private val service = scarletInstance.create<ChatService>()

  override fun observeWebSocketEvent(): Flow<WebSocketEvent>  = service.observeWebSocketEvent()

  override fun observeIncomingChat(): Flow<Chat>              = service.observeIncomingChat()

  override fun sendMessage(messageUpdate: MessageUpdate)      = service.sendMessage(messageUpdate)

  override fun observeIncomingMessage(): Flow<MessageUpdate>  = service.observeIncomingMessage()

  override fun observeUser(): Flow<UserUpdate>                = service.observeUser()

  override fun observeUserStatus(): Flow<UserStatusUpdate>    = service.observeUserStatus()
}
