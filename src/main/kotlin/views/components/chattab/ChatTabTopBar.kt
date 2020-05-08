package views.components.chattab

import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import models.AuthUserModel
import models.Chat
import models.User
import tornadofx.*
import views.components.chatlisttab.ChatList
import views.stylesheets.ChatTabStylesheet.Companion.closeButton
import views.stylesheets.MainStylesheet.Companion.recipientName
import views.stylesheets.ChatTabStylesheet.Companion.recipientStatus
import views.stylesheets.MainStylesheet.Companion.topBar
import views.stylesheets.ChatTabStylesheet.Companion.recipient as partnerID

class ChatTabTopBar : View() {
  private val recipient = find<AuthUserModel>().activeChat.select(Chat::recipientProperty)

  override val root = stackpane {
    addClass(topBar)

    hbox {
      vbox {
        setId(partnerID)
        hgrow = Priority.ALWAYS

        label(recipient.select(User::firstNameProperty)).addClass(recipientName)
        hbox {
          setId(recipientStatus)

          circle {
            radius = 5.0
            fill = Color.GRAY
          }
          label("Offline")
        }
      }
      vbox {
        setId(closeButton)

        button("Close").action {
          // Find ChatList and clear selection - TODO: replace with EventBuses in the future
          find<ChatList>().root.selectionModel.clearSelection()
        }
      }
    }
  }
}
