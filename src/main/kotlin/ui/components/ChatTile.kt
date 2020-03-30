package ui.components

import javafx.geometry.Pos
import tornadofx.*
import ui.MainStylesheet
import ui.MainStylesheet.Companion.avatarSize
import ui.MainStylesheet.Companion.chatTile
import ui.MainStylesheet.Companion.defaultSpacing
import ui.MainStylesheet.Companion.partnerName

class ChatTile : Fragment() {
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

      label("Name of the chat").addClass(partnerName)
      label("Here is my latest message")
    }
  }
}