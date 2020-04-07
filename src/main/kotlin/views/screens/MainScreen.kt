package views.screens

import controllers.MainController
import models.*
import tornadofx.*
import views.components.NoChatSelected

class MainScreen : View("Signus") {
  private val authUser = AuthUser("Masfik", "Masfik", "email@email.com")
  private val authUserModel: AuthUserModel by inject()
  private val controller: MainController by inject()

  init {
    controller.listenActiveChat()

    authUser.chats.addAll(
      Chat(242, User("Tom", "Caedan", "tom")),
      Chat(2, User("Masfik", "Username", "email"))
    )
    authUserModel.item = authUser
  }

  override val root = splitpane {
    setPrefSize(900.0, 600.0)
    setDividerPositions(0.35)

    this += ChatListTab::class
    this += NoChatSelected::class
  }
}