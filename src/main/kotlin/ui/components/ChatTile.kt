package ui.components

import javafx.geometry.Pos
import models.Chat
import models.ChatModel
import tornadofx.*
import ui.MainStylesheet.Companion.avatarSize
import ui.MainStylesheet.Companion.chatTile
import ui.MainStylesheet.Companion.defaultSpacing
import ui.MainStylesheet.Companion.partnerName

class ChatTile : ListCellFragment<Chat>() {
  private val chat = ChatModel().bindTo(this)

  override val root = hbox {
    setId(chatTile)
    spacing = defaultSpacing
    maxHeight = 50.0

    run {
      imageview("user.png") {
        fitHeight = avatarSize
        fitWidth = avatarSize
      }
    }
    vbox {
      alignment = Pos.CENTER_LEFT

      label(chat.partner.value?.name ?: "No name").addClass(partnerName)
      label("Here is my latest message")
    }
  }
}