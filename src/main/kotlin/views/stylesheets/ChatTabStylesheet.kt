package views.stylesheets

import javafx.geometry.Pos
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import tornadofx.*
import views.stylesheets.MainStylesheet.Companion.barProperties
import views.stylesheets.MainStylesheet.Companion.defaultSpacing
import views.stylesheets.MainStylesheet.Companion.hbox
import views.stylesheets.MainStylesheet.Companion.partnerName
import views.stylesheets.MainStylesheet.Companion.topBar
import views.stylesheets.MainStylesheet.Companion.vbox

class ChatTabStylesheet : Stylesheet() {
  companion object {
    val partner by cssid()
    val partnerStatus by cssid()
    val closeButton by cssid()
    val chatMessage by cssclass()
    val messageTime by cssclass()

    /* STYLING CONST */
    private const val chatSpacing = defaultSpacing * 1.4
  }

  init {
    topBar {
      partner {
        partnerName {
          fontWeight = FontWeight.BOLD
          padding = box(0.px, 0.px, 3.px, 0.px)
        }

        partnerStatus {
          spacing = (defaultSpacing / 2).px
          alignment = Pos.CENTER_LEFT
        }
      }

      closeButton {
        alignment = Pos.CENTER_RIGHT
      }
    }

    chatMessage {
      padding = box(chatSpacing - defaultSpacing.px)
      spacing = chatSpacing.px

      vbox {
        // Vertical spacing between the name of the user and message
        spacing = 3.px

        hbox {
          // Horizontal spacing between the name of the user and the message time
          spacing = chatSpacing.px

          messageTime {
            fontStyle = FontPosture.ITALIC
            fontSize = 8.pt
            textFill = SignusTheme.TEXT_ON_BG_DARKER
          }
        }
      }
    }

    form {
      +barProperties
      alignment = Pos.CENTER

      hbox {
        spacing = defaultSpacing.px
      }
    }
  }
}