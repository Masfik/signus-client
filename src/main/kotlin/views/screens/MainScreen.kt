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

    val tom = User("Tom", "Caedan", "tom")
    val tomChat = Chat(242, tom)
    tomChat.messageList.addAll(
      Message(1, "This is a simple test", tom),
      Message(2, "This is my latest message", tom)
    )

    authUser.chats.addAll(
      tomChat,
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