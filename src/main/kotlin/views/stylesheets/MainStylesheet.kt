package views.stylesheets

import javafx.scene.effect.DropShadow
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class MainStylesheet : Stylesheet() {
  companion object {
    /* CLASSES AND IDs */
    val topBar by cssclass()
    val chatTile by cssclass()
    val partnerName by cssclass()

    /* STYLING CONST */
    const val defaultSpacing = 10.0
    fun ImageView.avatarSize() { fitHeight = 50.0; fitWidth = fitHeight }
    val dropShadow = DropShadow(5.0, SignusColour.PRIMARY_DARKER)
  }

  init {
    /* UI COMPONENTS*/
    "*" {
      textFill = SignusColour.TEXT_ON_BG
    }

    root {
      backgroundColor += SignusColour.BACKGROUND
    }

    s(splitPane, listView) {
      padding = box(0.px)
      backgroundInsets += box(0.px)

      splitPaneDivider {
        padding = box(0.0.px, 1.0.px)
        backgroundColor += SignusColour.PRIMARY_DARKER
      }
    }

    listView {
      backgroundColor += Color.TRANSPARENT

      listCell {
        and(odd, even) {
          backgroundColor += Color.TRANSPARENT
        }
        and(selected) {
          backgroundColor += SignusColour.SECONDARY

          label {
            textFill = SignusColour.TEXT_ON_SECONDARY
          }
        }
      }
    }

    textField {
      backgroundColor += SignusColour.INPUT
      promptTextFill = SignusColour.PROMPT_TEXT

      and(focused) {
        borderInsets += box(0.px)
      }
    }

    button {
      backgroundColor += SignusColour.SECONDARY
      textFill = SignusColour.TEXT_ON_SECONDARY
    }

    scrollBar {
      backgroundColor += SignusColour.PRIMARY
      prefWidth = defaultSpacing.px

      track {
        backgroundColor += SignusColour.PRIMARY
      }

      s(thumb, incrementButton, decrementButton) {
        backgroundColor += SignusColour.PRIMARY_DARKER
        borderColor += box(SignusColour.PRIMARY)
      }

      s(decrementArrow, incrementArrow) {
        backgroundColor += SignusColour.TEXT_ON_BG
      }
    }


    /* APP CLASSES */
    topBar {
      backgroundColor += SignusColour.PRIMARY
      prefHeight = 50.0.px
    }

    chatTile {
      padding = box(defaultSpacing.px)
      maxHeight = 50.0.px
    }

    partnerName {
      fontWeight = FontWeight.BOLD
    }
  }
}