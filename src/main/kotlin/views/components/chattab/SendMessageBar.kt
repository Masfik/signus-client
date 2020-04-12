package views.components.chattab

import controllers.ChatTabController
import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import tornadofx.*

class SendMessageBar : View() {
  private val controller: ChatTabController by inject()
  var message: TextField by singleAssign()

  override val root = form {
    hbox {
      message = textfield {
        promptText = "Type a message"
        hgrow = Priority.ALWAYS
      }
      button("Send") {
        isDefaultButton = true // <- Allows users to submit when they press the ENTER key
        action { controller.submit(message) }
      }
    }
  }
}
