package views.stylesheets

import javafx.geometry.Pos
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import tornadofx.*
import views.stylesheets.MainStylesheet.Companion.defaultSpacing
import views.stylesheets.MainStylesheet.Companion.partnerName
import views.stylesheets.MainStylesheet.Companion.topBar

class ChatTabStylesheet : Stylesheet() {
  companion object {
    val partner by cssid()
    val partnerStatus by cssid()
    val closeButton by cssid()
    val chatMessage by cssclass()
    val messageTime by cssclass()
  }
  
  init {
    topBar {
      partner {
        partnerName {
          fontWeight = FontWeight.BOLD
          padding = box(0.0.px, 0.0.px, 3.0.px, 0.0.px)
        }

        partnerStatus {
          alignment = Pos.CENTER_LEFT
        }
      }

      closeButton {
        alignment = Pos.CENTER_RIGHT
      }
    }

    chatMessage {
      padding = box(defaultSpacing.px)

      messageTime {
        fontStyle = FontPosture.ITALIC
        fontSize = 8.pt
        textFill = SignusTheme.TEXT_ON_BG_DARKER
      }
    }

    form {
      backgroundColor += SignusTheme.PRIMARY
    }
  }
}