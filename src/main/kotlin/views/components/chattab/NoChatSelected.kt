package views.components.chattab

import tornadofx.View
import tornadofx.label
import tornadofx.stackpane

class NoChatSelected : View() {
  override val root = stackpane { label("No chat selected") }
}
