package views.stylesheets

import javafx.scene.effect.DropShadow
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import org.controlsfx.glyphfont.GlyphFont
import org.controlsfx.glyphfont.GlyphFontRegistry
import tornadofx.*

class MainStylesheet : Stylesheet() {
  companion object {
    /* CLASSES AND IDs */
    val topBar by cssclass()
    val chatTile by cssclass()
    val partnerName by cssclass()

    /* STYLING CONST */
    val fontAwesome: GlyphFont = GlyphFontRegistry.font("FontAwesome")
    const val defaultSpacing = 10.0
    fun ImageView.avatarSize() { fitHeight = 50.0; fitWidth = fitHeight }
    val dropShadow = DropShadow(5.0, SignusTheme.PRIMARY_DARKER)
  }

  init {
    /* UI COMPONENTS*/
    "*" {
      textFill = SignusTheme.TEXT_ON_BG
    }

    root {
      backgroundColor += SignusTheme.BACKGROUND
    }

    s(splitPane, listView) {
      padding = box(0.px)
      backgroundInsets += box(0.px)

      splitPaneDivider {
        padding = box(0.0.px, 1.0.px)
        backgroundColor += SignusTheme.PRIMARY_DARKER
      }
    }

    listView {
      backgroundColor += Color.TRANSPARENT

      listCell {
        and(odd, even) {
          backgroundColor += Color.TRANSPARENT
        }
        and(selected) {
          backgroundColor += SignusTheme.SECONDARY

          label {
            textFill = SignusTheme.TEXT_ON_SECONDARY
          }
        }
      }
    }

    textField {
      backgroundColor += SignusTheme.INPUT
      promptTextFill = SignusTheme.PROMPT_TEXT

      and(focused) {
        borderInsets += box(0.px)
      }
    }

    button {
      backgroundColor += SignusTheme.SECONDARY
      textFill = SignusTheme.TEXT_ON_SECONDARY

      and(hover) {
        backgroundColor += SignusTheme.SECONDARY.derive(-0.1)
      }
    }

    scrollBar {
      backgroundColor += SignusTheme.PRIMARY
      // Changes size of the scrollbar (including icons)
      fontSize = defaultSpacing - (defaultSpacing/5).px

      s(track) {
        backgroundColor += SignusTheme.PRIMARY
      }

      s(thumb, incrementButton, decrementButton) {
        backgroundColor += SignusTheme.PRIMARY_DARKER
        //borderColor += box(SignusTheme.PRIMARY)
      }

      s(decrementArrow, incrementArrow) {
        backgroundColor += SignusTheme.TEXT_ON_BG
      }
    }


    /* APP CLASSES */
    topBar {
      backgroundColor += SignusTheme.PRIMARY
      prefHeight = 50.0.px
      padding = box((defaultSpacing/2).px, defaultSpacing.px, (defaultSpacing/2).px, defaultSpacing.px)
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