import tornadofx.*
import views.MainStylesheet
import views.screens.MainScreen

class Signus : App(MainScreen::class, MainStylesheet::class)

fun main(args: Array<String>) = launch<Signus>(args)