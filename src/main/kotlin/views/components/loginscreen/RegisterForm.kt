package views.components.loginscreen

import tornadofx.*
import controllers.AuthenticationController
import javafx.beans.property.SimpleStringProperty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.ViewTransition.Direction.RIGHT
import tornadofx.ViewTransition.Slide
import views.stylesheets.LoginStylesheet

class RegisterForm : View() {
  private val model = ViewModel()
  private val firstName = model.bind { SimpleStringProperty() }
  private val lastName = model.bind { SimpleStringProperty() }
  private val email = model.bind { SimpleStringProperty() }
  private val username = model.bind { SimpleStringProperty() }
  private val password = model.bind { SimpleStringProperty() }
  private val confirmPassword = model.bind { SimpleStringProperty() }

  private val controller: AuthenticationController by inject()

  override val root = vbox {
    form {
      fieldset("First Name") {
        textfield(firstName).required()
      }
      fieldset("Last name") {
        textfield(lastName).required()
      }
      fieldset("Email") {
        textfield(email).required()
      }
      fieldset("Username") {
        textfield(username).required()
      }
      fieldset("Password") {
        passwordfield(password).required()
      }
      fieldset("Confirm Password") {
        passwordfield(confirmPassword).required()
      }
      button("Register") {
        isDefaultButton = true
        enableWhen(model.valid)
        action {
          GlobalScope.launch(Dispatchers.IO) {
            controller.register(
              firstName.value,
              lastName.value,
              email.value,
              username.value,
              password.value,
              confirmPassword.value
            )
          }
        }
      }
      label("Login") {
        setId(LoginStylesheet.register)

        setOnMouseClicked {
          find<RegisterForm>().replaceWith<LoginForm>(Slide(0.3.seconds, RIGHT))
        }
      }
    }
    label(controller.errorProperty).setId(LoginStylesheet.error)
  }
}