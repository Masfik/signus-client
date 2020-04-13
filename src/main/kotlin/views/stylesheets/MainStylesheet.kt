package views.stylesheets

import javafx.geometry.Pos
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
    val barProperties = mixin {
      backgroundColor += SignusTheme.PRIMARY
      prefHeight = 55.px
      padding = box((defaultSpacing/2).px, defaultSpacing.px, (defaultSpacing/2).px, defaultSpacing.px)
    }
    fun ImageView.avatarSize() { fitHeight = defaultSpacing * 5; fitWidth = fitHeight }
    val dropShadow = DropShadow(5.0, SignusTheme.PRIMARY_DARKER)
  }

  init {
    /* UI COMPONENTS (Nodes) */
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
      prefHeight = 33.px
      backgroundColor += SignusTheme.INPUT
      promptTextFill = SignusTheme.PROMPT_TEXT

      and(focused) {
        borderInsets += box(0.px)
      }
    }

    button {
      prefHeight = 33.px
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
      }

      s(decrementArrow, incrementArrow) {
        backgroundColor += SignusTheme.TEXT_ON_BG
      }
    }


    /* APP CLASSES */
    topBar {
      +barProperties

      "VBox" {
        alignment = Pos.CENTER_LEFT
      }

      "HBox" {
        spacing = defaultSpacing.px
      }
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