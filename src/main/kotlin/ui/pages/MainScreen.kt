package ui.pages

import models.*
import tornadofx.*
import ui.components.ChatListView
import ui.pages.chat.ChatTab
import javax.json.JsonObject
import javax.json.JsonObjectBuilder

class MainScreen : View("Signus") {
  private val authScope = Scope()
  private val authUser = AuthUser("Masfik", "Masfik", "contact@masfik.net")
  private val authUserModel = AuthUserModel()

  init {
    authUser.chats.addAll(
      Chat(1, User("Tom", "Caedan", "tom")),
      Chat(2, User("Name", "Username", "email"))
    )

    authUserModel.item = authUser
    println(authUserModel.chats.value[0].messageList[0].data)
    setInScope(authUserModel, authScope)
  }

  override val root = splitpane {
    setPrefSize(900.0, 600.0)
    setDividerPositions(0.3)

    this += find<ChatListView>(authScope)
    this += find<ChatTab>()
  }
}