package views.components

import controllers.ChatTabController
import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import tornadofx.*
import views.stylesheets.MainStylesheet

class SendMessageBar : View() {
  private val controller: ChatTabController by inject()
  private var message: TextField by singleAssign()

  override val root = form {
    hbox {
      spacing = MainStylesheet.defaultSpacing

      message = textfield {
        promptText = "Type a message"
        hgrow = Priority.ALWAYS

        // Submit by pressing the "Enter" key
        action { controller.submit(message) }
      }
      button("Send").action { controller.submit(message) }
    }
  }
}
