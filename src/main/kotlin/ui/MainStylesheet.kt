package ui

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*

class MainStylesheet : Stylesheet() {
  companion object {
    val chatTile by cssid()
    val partnerName by cssclass()

    val chatScreen by cssid()
    val chatTopBar by cssid()
    val partner by cssid()
    val chatMessage by cssclass()
    val messageTime by cssclass()

    /* STYLING CONST */
    const val defaultSpacing = 10.0
    const val avatarSize = 50.0
  }

  init {
    chatTile {
      padding = box(defaultSpacing.px)
      backgroundColor += Color.ALICEBLUE
    }

    partnerName {
      fontWeight = FontWeight.BOLD
    }

    chatScreen {
      chatTopBar {
        prefHeight = 50.0.px
        backgroundColor += Color.WHITE

        partner {
          alignment = Pos.CENTER_LEFT
          padding = box(0.0.px, 0.0.px, 0.0.px, defaultSpacing.px)

          partnerName {
            fontWeight = FontWeight.BOLD
            padding = box(0.0.px, 0.0.px, 3.0.px, 0.0.px)
          }
        }
      }

      chatMessage {
        padding = box(defaultSpacing.px)

        messageTime {
          fontStyle = FontPosture.ITALIC
          fontSize = 8.pt
        }
      }

      form {
        backgroundColor += Color.WHITE
      }
    }
  }
}