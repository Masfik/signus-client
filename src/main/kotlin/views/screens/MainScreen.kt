package views.screens

import controllers.MainController
import tornadofx.*
import views.components.chattab.NoChatSelected

class MainScreen : View("Signus") {
  private val controller: MainController by inject()

  init { controller.listenActiveChat() }

  override val root = splitpane {
    setDividerPositions(0.35)

    this += ChatListTab::class
    this += NoChatSelected::class
  }
}