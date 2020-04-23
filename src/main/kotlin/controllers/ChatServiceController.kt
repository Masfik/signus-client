package controllers

import com.tinder.scarlet.*
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import models.ServerSettingsModel
import okhttp3.OkHttpClient
import tornadofx.Controller

class ChatServiceController : Controller() {
  val serverSettings: ServerSettingsModel by inject()

  private val scarletInstance = Scarlet.Builder()
    .webSocketFactory(OkHttpClient().newWebSocketFactory(""))
    .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
}
