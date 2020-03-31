package ui.pages

import tornadofx.*
import ui.components.ChatListView
import ui.pages.chat.ChatTab

class MainScreen : View("Signus") {
  override val root = splitpane {
    setPrefSize(900.0, 600.0)
    setDividerPositions(0.3)

    this += find(ChatListView::class)
    this += find(ChatTab::class)
  }
}