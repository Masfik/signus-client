package views.components.chattab

import models.Message
import models.MessageModel
import models.User
import tornadofx.*
import views.stylesheets.ChatTabStylesheet.Companion.chatMessage
import views.stylesheets.ChatTabStylesheet.Companion.messageTime
import views.stylesheets.MainStylesheet.Companion.avatarSize
import views.stylesheets.MainStylesheet.Companion.partnerName as partnerNameClass
import views.stylesheets.MainStylesheet.Companion.defaultSpacing

class MessageTile : ListCellFragment<Message>() {
  private val message = MessageModel().bindTo(this)
  private val partnerName = message.sender.select(User::nameProperty)
  private val data = message.data

  override val root = hbox {
    spacing = defaultSpacing
    addClass(chatMessage)

    run {
      imageview("user.png").avatarSize()
    }

    vbox {
      spacing = 3.0

      hbox {
        spacing = defaultSpacing

        label(partnerName).addClass(partnerNameClass)
        label("Today at 05:14").addClass(messageTime)
      }

      // TODO: For the moment, only text is show to the user
      label(data).isWrapText = true
    }
  }
}