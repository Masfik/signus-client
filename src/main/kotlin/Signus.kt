import tornadofx.*
import views.screens.MainScreen
import views.utils.MainStylesheet

class Signus : App(MainScreen::class, MainStylesheet::class)

fun main(args: Array<String>) = launch<Signus>(args)