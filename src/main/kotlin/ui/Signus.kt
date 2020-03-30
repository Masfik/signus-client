package ui

import tornadofx.*
import ui.pages.main.MainScreen

class Signus : App(MainScreen::class, MainStylesheet::class) {
  init {
    reloadStylesheetsOnFocus()
  }
}