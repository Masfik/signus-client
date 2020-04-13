package views.components.loginscreen

import controllers.LoginController
import javafx.beans.property.SimpleStringProperty
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class LoginForm : View() {
  private val model = ViewModel()
  private val username = model.bind { SimpleStringProperty() }
  private val password = model.bind { SimpleStringProperty() }

  private val controller: LoginController by inject()

  override val root = stackpane {
    form {
      fieldset("Username") {
        textfield(username).required()
      }
      fieldset("Password") {
        passwordfield(password).required()
      }
      button("Login") {
        enableWhen(model.valid)
        isDefaultButton = true

        action {
          runAsyncWithProgress {
            controller.login(username.value, password.value)
          }
        }
      }
    }
    label(controller.statusProperty) {
      style {
        paddingTop = 10
        textFill = Color.RED
        fontWeight = FontWeight.BOLD
      }
    }
  }
}
