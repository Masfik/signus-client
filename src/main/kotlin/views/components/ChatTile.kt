package views.components

import javafx.geometry.Pos
import models.Chat
import models.ChatModel
import models.User
import tornadofx.*
import views.utils.MainStylesheet.Companion.avatarSize
import views.utils.MainStylesheet.Companion.chatTile
import views.utils.MainStylesheet.Companion.defaultSpacing
import views.utils.MainStylesheet.Companion.partnerName

class ChatTile : ListCellFragment<Chat>() {
  private val chat = ChatModel().bindTo(this)

  override val root = hbox {
    addClass(chatTile)
    spacing = defaultSpacing

    run {
      imageview("user.png").avatarSize()
    }
    vbox {
      alignment = Pos.CENTER_LEFT

      label(chat.partner.select(User::nameProperty)).addClass(partnerName)
      label(chat.preview)
    }
  }
}