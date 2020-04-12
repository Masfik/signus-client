package views.screens

import controllers.MainController
import models.*
import tornadofx.*
import views.components.chattab.NoChatSelected

class MainScreen : View("Signus") {
  private val authUser = AuthUser("Masfik", "Masfik", "email@email.com")
  private val authUserModel: AuthUserModel by inject()
  private val controller: MainController by inject()

  init {
    controller.listenActiveChat()

    val tom = User("Tom", "Caedan", "tom")
    val tomChat = Chat(242, tom)

    authUser.chats.addAll(
      tomChat,
      Chat(2, User("Masfik", "Username", "email"))
    )
    authUserModel.item = authUser
  }

  override val root = splitpane {
    setDividerPositions(0.35)

    this += ChatListTab::class
    this += NoChatSelected::class
  }
}