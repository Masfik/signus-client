package views.stylesheets

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import tornadofx.*
import views.stylesheets.MainStylesheet.Companion.vbox
import views.stylesheets.MainStylesheet.Companion.defaultSpacing as defaultSpacing1

class LoginStylesheet : Stylesheet() {
  companion object {
    val error by cssid()
    val register by cssid()
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

      register {
        padding = box(defaultSpacing1.px)
      }
    }

    error {
      padding = box(defaultSpacing1.px)
      textFill = SignusTheme.RED
      fontWeight = FontWeight.BOLD
    }
  }
}