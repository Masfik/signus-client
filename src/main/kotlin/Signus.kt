import tornadofx.App
import tornadofx.launch
import views.screens.LoginScreen
import views.stylesheets.MainStylesheet

class Signus : App(LoginScreen::class, MainStylesheet::class)

fun main() = launch<Signus>()