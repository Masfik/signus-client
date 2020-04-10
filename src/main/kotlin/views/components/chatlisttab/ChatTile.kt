package views.components.chatlisttab

import javafx.geometry.Pos
import models.Chat
import models.ChatModel
import models.User
import tornadofx.*
import views.stylesheets.MainStylesheet.Companion.avatarSize
import views.stylesheets.MainStylesheet.Companion.chatTile
import views.stylesheets.MainStylesheet.Companion.defaultSpacing
import views.stylesheets.MainStylesheet.Companion.partnerName

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