package views.components.chatlisttab

import javafx.geometry.Pos
import models.Chat
import models.ChatModel
import models.User
import tornadofx.*
import views.stylesheets.MainStylesheet.Companion.recipientName as recipientNameClass
import views.stylesheets.MainStylesheet.Companion.avatarSize
import views.stylesheets.MainStylesheet.Companion.chatTile

class ChatTile : ListCellFragment<Chat>() {
  private val chat = ChatModel().bindTo(this)
  private val recipientName = chat.recipient.select(User::fullNameProperty)
  private val preview = chat.select(Chat::previewProperty)

  override val root = hbox {
    addClass(chatTile)

    run {
      imageview("user.png").avatarSize()
    }
    vbox {
      alignment = Pos.CENTER_LEFT

      label(recipientName).addClass(recipientNameClass)
      label(preview)
    }
  }
}