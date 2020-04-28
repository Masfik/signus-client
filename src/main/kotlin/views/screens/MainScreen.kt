package views.screens

import controllers.MainController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.*
import views.components.chattab.NoChatSelected

class MainScreen : View("Signus") {
  private val controller: MainController by inject()

  init {
    controller.listenActiveChat()
    GlobalScope.launch { controller.observeServerConnection() }
    GlobalScope.launch { controller.observeUser() }
  }

  override val root = splitpane {
    setDividerPositions(0.35)

    this += ChatListTab::class
    this += NoChatSelected::class
  }
}