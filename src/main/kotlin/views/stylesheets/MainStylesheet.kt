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
    val dropShadow = DropShadow(8.0, Color.LIGHTGRAY)
  }

  init {
    /* UI COMPONENTS*/
    "*" {
      textFill = SignusColour.TEXT_ON_BG.value
    }

    root {
      backgroundColor += SignusColour.BACKGROUND.value
    }

    s(splitPane, listView) {
      padding = box(0.px)
      backgroundInsets += box(0.px)
      splitPaneDivider {
        padding = box(0.0.px, 1.0.px)
        backgroundColor += SignusColour.PRIMARY_DARKER.value
      }
    }

    listView {
      backgroundColor += Color.TRANSPARENT
      listCell {
        and(odd, even) {
          backgroundColor += Color.TRANSPARENT
        }
        and(selected) {
          backgroundColor += SignusColour.SECONDARY.value
          // TODO change selected text colour
        }
      }
    }

    textField {
      backgroundColor += SignusColour.INPUT.value
      promptTextFill = SignusColour.PROMPT_TEXT.value
      and(focused) {
        borderInsets += box(0.px)
      }
    }

    button {
      backgroundColor += SignusColour.SECONDARY.value
      textFill = SignusColour.TEXT_ON_SECONDARY.value
    }


    /* APP CLASSES */
    topBar {
      backgroundColor += SignusColour.PRIMARY.value
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