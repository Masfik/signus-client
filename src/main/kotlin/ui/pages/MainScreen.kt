package ui.pages

import tornadofx.*
import ui.components.ChatTile

class MainScreen : View("Signus") {
  override val root = splitpane {
    setDividerPositions(0.3)
  }

  init {
    root += find(ChatTile::class)
    root += find(SomethingElse::class)
  }
}

class SomethingElse : View() {
  override val root = vbox {
    label("No chat selected")
  }
}