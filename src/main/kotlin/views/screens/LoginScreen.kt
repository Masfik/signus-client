package views.screens

import controllers.LoginController
import javafx.beans.property.SimpleStringProperty
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import org.controlsfx.glyphfont.FontAwesome.Glyph
import tornadofx.*
import views.stylesheets.LoginStylesheet
import views.stylesheets.MainStylesheet.Companion.defaultSpacing
import views.stylesheets.MainStylesheet.Companion.fontAwesome

class LoginScreen : View("Signus - Login") {
  private val model = ViewModel()
  private val username = model.bind { SimpleStringProperty() }
  private val password = model.bind { SimpleStringProperty() }
  private val loginController: LoginController by inject()

  override val root = borderpane {
    addStylesheet(LoginStylesheet::class)
    setPrefSize(900.0, 600.0)

    top {
      paddingAll = defaultSpacing
      button("SERVER SETTINGS", fontAwesome.create(Glyph.GEAR)).action {
        find<ServerSettings>().openModal()
      }
    }

    center {
      stackpane {
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
                loginController.login(username.value, password.value)
              }
            }
          }
        }
        label(loginController.statusProperty) {
          style {
            paddingTop = 10
            textFill = Color.RED
            fontWeight = FontWeight.BOLD
          }
        }
      }
    }
  }
}