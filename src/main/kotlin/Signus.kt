import tornadofx.*
import ui.MainStylesheet
import ui.pages.MainScreen

class Signus : App(MainScreen::class, MainStylesheet::class)

fun main(args: Array<String>) = launch<Signus>(args)