package views.components.chattab

import models.Message
import models.MessageModel
import models.User
import tornadofx.*
import views.stylesheets.ChatTabStylesheet.Companion.chatMessage
import views.stylesheets.ChatTabStylesheet.Companion.messageTime as messageTimeClass
import views.stylesheets.MainStylesheet.Companion.avatarSize
import views.stylesheets.MainStylesheet.Companion.recipientName as recipientNameClass

class MessageTile : ListCellFragment<Message>() {
  private val message = MessageModel().bindTo(this)
  private val sender = message.sender.select(User::fullNameProperty)
  private val time = message.select(Message::formattedDateTimeProperty)
  private val data = message.data

  override val root = hbox {
    addClass(chatMessage)

    run {
      imageview("user.png").avatarSize()
    }
    vbox {
      hbox {
        label(sender).addClass(recipientNameClass)
        label(time).addClass(messageTimeClass)
      }
      // TODO: For the moment, only text is shown to the user
      label(data).isWrapText = true
    }
  }
}