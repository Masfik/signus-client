package views.screens

import tornadofx.*
import views.components.loginscreen.LoginForm
import views.components.loginscreen.LoginTopBar
import views.stylesheets.LoginStylesheet
import views.stylesheets.MainStylesheet.Companion.defaultSpacing

class LoginScreen : View("Signus - Login") {
  override val root = borderpane {
    addStylesheet(LoginStylesheet::class)
    setPrefSize(900.0, 600.0)

    top {
      paddingAll = defaultSpacing
      this += LoginTopBar::class
    }

    center<LoginForm>()
  }
}