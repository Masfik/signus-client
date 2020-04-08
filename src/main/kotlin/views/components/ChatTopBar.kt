package views.components

import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import models.AuthUserModel
import models.Chat
import models.User
import tornadofx.*
import views.utils.MainStylesheet.Companion.closeButton
import views.utils.MainStylesheet.Companion.partnerName
import views.utils.MainStylesheet.Companion.partnerStatus
import views.utils.MainStylesheet.Companion.topBar
import views.screens.ChatListTab
import views.utils.MainStylesheet.Companion.partner as partnerID

class ChatTopBar : View() {
  private val partner = find<AuthUserModel>().activeChat.select(Chat::partnerProperty)

  override val root = stackpane {
    addClass(topBar)

    hbox {
      vbox {
        setId(partnerID)
        hgrow = Priority.ALWAYS

        label(partner.select(User::nameProperty)).addClass(partnerName)
        hbox {
          setId(partnerStatus)

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
          find<ChatListTab>().root.selectionModel.clearSelection()
        }
      }
    }
  }
}