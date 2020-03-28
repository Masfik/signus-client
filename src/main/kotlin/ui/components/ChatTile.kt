package ui.components

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class ChatTile : Fragment() {
  override val root = hbox {
    run {
      imageview("user.png") {
        fitHeight = 50.0
        fitWidth = 50.0
      }
    }
    vbox {
      alignment = Pos.CENTER_LEFT

      label("Name of the chat") {
        style {
          fontWeight = FontWeight.BOLD
        }
      }
      label("Here is my latest message")
    }

    spacing = 10.0
    maxHeight = 50.0
    style {
      paddingAll = 10
      backgroundColor = multi(Color.ALICEBLUE)
    }
  }
}