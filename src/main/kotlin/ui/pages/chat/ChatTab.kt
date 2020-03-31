package ui.pages.chat

import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import tornadofx.*
import ui.MainStylesheet.Companion.chatScreen
import ui.MainStylesheet.Companion.chatTopBar
import ui.MainStylesheet.Companion.defaultSpacing
import ui.MainStylesheet.Companion.partner
import ui.MainStylesheet.Companion.partnerName
import ui.components.Message

class ChatTab : View() {
  private val chatTabController: ChatTabController by inject()
  private var message: TextField by singleAssign()

  init {
    title = "Name of the person"
  }

  override val root = borderpane {
    setId(chatScreen)

    top {
      stackpane {
        setId(chatTopBar)

        vbox {
          setId(partner)

          label(title).addClass(partnerName)
          hbox {
            alignment = Pos.CENTER_LEFT
            spacing = 5.0
            circle {
              radius = 5.0
              fill = Color.GRAY
            }
            label("Offline")
          }
        }
      }
    }

    center {
      vbox {
        alignment = Pos.BOTTOM_LEFT

        for (i: Int in 0..5) this += find(Message::class)
      }
    }

    bottom {
      form {
        hbox {
          spacing = defaultSpacing

          message = textfield {
            hgrow = Priority.ALWAYS
            // Submit by pressing the "Enter" key
            action {
              chatTabController.submit(message)
            }
          }
          button("Send").action {
            chatTabController.submit(message)
          }
        }
      }
    }
  }
}
