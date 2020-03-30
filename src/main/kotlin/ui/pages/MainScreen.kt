package ui.pages

import tornadofx.*
import ui.components.ChatTile

class MainScreen : View("Signus") {
  override val root = splitpane {
    setPrefSize(900.0, 600.0)
    setDividerPositions(0.3)

    vbox {
      for (i: Int in 0..5) this += find(ChatTile::class)
    }
    this += find(ChatScreen::class)
  }
}