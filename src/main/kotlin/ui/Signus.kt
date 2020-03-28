package ui

import javafx.scene.Scene
import tornadofx.*
import ui.pages.MainScreen

class Signus : App(MainScreen::class) {
  override fun createPrimaryScene(view: UIComponent): Scene = Scene(view.root, 900.0, 600.0)
}