package ui.pages

import models.*
import tornadofx.*
import ui.components.ChatListView
import ui.pages.chat.ChatTab

class MainScreen : View("Signus") {
  private val authUser = AuthUser("Masfik", "Masfik", "email@email.com")
  private val authUserModel: AuthUserModel by inject()

  init {
    authUser.chats.addAll(
      Chat(242, User("Tom", "Caedan", "tom")),
      Chat(2, User("Masfik", "Username", "email"))
    )

    authUserModel.item = authUser
  }

  override val root = splitpane {
    setPrefSize(900.0, 600.0)
    setDividerPositions(0.35)

    this += ChatListView::class
    this += ChatTab::class
  }
}