package views.stylesheets

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import tornadofx.Stylesheet
import tornadofx.infinity
import tornadofx.px

class LoginStylesheet : Stylesheet() {
  init {
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
  }
}