package views.stylesheets

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import tornadofx.*
import views.stylesheets.MainStylesheet.Companion.defaultSpacing
import views.stylesheets.MainStylesheet.Companion.vbox

class LoginStylesheet : Stylesheet() {
  companion object {
    val error by cssid()
  }

  init {
    vbox {
      alignment = Pos.CENTER
    }

    form {
      alignment = Pos.CENTER
      maxWidth = 350.px

      label {
        fontWeight = FontWeight.NORMAL
      }

      button {
        prefWidth = infinity
      }
    }

    error {
      padding = box(defaultSpacing.px)
      textFill = SignusTheme.RED
      fontWeight = FontWeight.BOLD
    }
  }
}