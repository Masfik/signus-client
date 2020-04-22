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
    /* Classes and IDs */
    val topBar by cssclass()
    val chatTile by cssclass()
    val recipientName by cssclass()

    /* Misc */
    val fontAwesome: GlyphFont = GlyphFontRegistry.font("FontAwesome")

    /* Styling const */
    const val defaultSpacing = 10.0
    private val dropShadow = DropShadow(5.0, SignusTheme.PRIMARY_DARKER)

    /* Styling mixins */
    val barProperties = mixin {
      backgroundColor += SignusTheme.PRIMARY
      prefHeight = 55.px
      padding = box((defaultSpacing/2).px, defaultSpacing.px, (defaultSpacing/2).px, defaultSpacing.px)
    }
    fun ImageView.avatarSize() { fitHeight = 50.0; fitWidth = fitHeight }

    /* JavaFX Nodes */
    val vbox by csselement("VBox")
    val hbox by csselement("HBox")

    /* ControlsFX PopOver */
    val popOver by cssclass("popover")
    val content by cssclass("content")
    val title by cssclass("title")
    val icon by cssclass("icon")
    val graphics by cssclass("graphics")
    val circle by cssclass("circle")
    val line by cssclass("line")
    val text by cssclass("text")
    val border by cssclass("border")
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
        padding = box(defaultSpacing.px)

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

    popOver {
      content {
        backgroundColor += SignusTheme.BACKGROUND

        title {
          text {
            unsafe("-fx-text-fill", raw(SignusTheme.TEXT_ON_BG_DARKER.css + "!important"))
          }

          // Close button
          icon {
            graphics {
              circle {
                unsafe("-fx-fill", raw(SignusTheme.RED.css + "!important"))
              }
            }
          }
        }
      }

      // The small arrow (caret) that appears on the left of the PopOver
      border {
        strokeWidth = 0.px
        unsafe("-fx-fill", raw(SignusTheme.BACKGROUND.css + "!important"))
      }
    }

    tooltip {
      backgroundColor += SignusTheme.BACKGROUND.darker()
    }


    /* APP CLASSES */
    topBar {
      +barProperties

      vbox {
        alignment = Pos.CENTER_LEFT
      }

      hbox {
        spacing = defaultSpacing.px
      }
    }

    chatTile {
      maxHeight = 50.px
      spacing = defaultSpacing.px
    }

    recipientName {
      fontWeight = FontWeight.BOLD
    }
  }
}