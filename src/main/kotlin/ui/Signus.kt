package ui

import tornadofx.*
import ui.pages.MainScreen

class Signus : App(MainScreen::class, MainStylesheet::class) {
  init {
    reloadStylesheetsOnFocus()
  }
}