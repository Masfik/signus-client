package ui.pages.chat

import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import models.AuthUserModel
import models.Chat
import models.User
import tornadofx.*
import ui.MainStylesheet.Companion.chatScreen
import ui.MainStylesheet.Companion.topBar
import ui.MainStylesheet.Companion.defaultSpacing
import ui.MainStylesheet.Companion.partner as partnerID
import ui.MainStylesheet.Companion.partnerName
import ui.components.Message

class ChatTab : View() {
  private val chatTabController: ChatTabController by inject()

  private val authUser: AuthUserModel by inject()
  private val partner = authUser.activeChat.select(Chat::partnerProperty)

  private var message: TextField by singleAssign()

  override val root = borderpane {
    setId(chatScreen)

    top {
      stackpane {
        addClass(topBar)

        vbox {
          setId(partnerID)

          label(partner.select(User::nameProperty)).addClass(partnerName)
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
