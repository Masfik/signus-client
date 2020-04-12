import tornadofx.*
import views.screens.LoginScreen
import views.stylesheets.MainStylesheet

class Signus : App(LoginScreen::class, MainStylesheet::class)

fun main(args: Array<String>) = launch<Signus>(args)