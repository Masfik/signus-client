package ui

import javafx.geometry.Pos
import javafx.scene.effect.DropShadow
import javafx.scene.paint.Color
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import tornadofx.*

class MainStylesheet : Stylesheet() {
  companion object {
    val topBar by cssclass()
    val chatTile by cssclass()
    val partnerName by cssclass()

    val chatScreen by cssid()
    val partner by cssid()
    val chatMessage by cssclass()
    val messageTime by cssclass()

    /* STYLING CONST */
    const val defaultSpacing = 10.0
    const val avatarSize = 50.0
    private val dropShadow = DropShadow(5.0, Color.GRAY)
  }

  init {
    splitPane {
      padding = box(0.px)
    }

    topBar {
      backgroundColor += Color.WHITE
      prefHeight = 50.0.px
      effect = dropShadow
    }

    chatTile {
      padding = box(5.px)
    }

    partnerName {
      fontWeight = FontWeight.BOLD
    }

    chatScreen {
      topBar {
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
        effect = dropShadow
        backgroundColor += Color.WHITE
      }
    }
  }
}