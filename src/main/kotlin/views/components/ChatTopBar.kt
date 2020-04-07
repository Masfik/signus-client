package views.components

import javafx.geometry.Pos
import javafx.scene.paint.Color
import models.AuthUserModel
import models.Chat
import models.User
import tornadofx.*
import views.MainStylesheet

class ChatTopBar : View() {
  private val partner = find(AuthUserModel::class).activeChat.select(Chat::partnerProperty)

  override val root = stackpane {
    addClass(MainStylesheet.topBar)

    vbox {
      setId(MainStylesheet.partner)

      label(partner.select(User::nameProperty)).addClass(MainStylesheet.partnerName)
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
