package views.components.loginscreen

import controllers.LoginController
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import views.stylesheets.LoginStylesheet.Companion.error

class LoginForm : View() {
  private val model = ViewModel()
  private val username = model.bind { SimpleStringProperty() }
  private val password = model.bind { SimpleStringProperty() }

  private val controller: LoginController by inject()

  override val root = vbox {
    form {
      fieldset("Username") {
        textfield(username).required()
      }
      fieldset("Password") {
        passwordfield(password).required()
      }
      button("Login") {
        isDefaultButton = true
        enableWhen(model.valid)
        action {
          runAsyncWithProgress {
            controller.login(username.value, password.value)
          }
        }
      }
    }
    label(controller.errorProperty).setId(error)
  }
}
