package views.components

import models.Message
import models.MessageModel
import models.User
import tornadofx.*
import views.utils.MainStylesheet
import views.utils.MainStylesheet.Companion.avatarSize

class MessageTile : ListCellFragment<Message>() {
  private val message = MessageModel().bindTo(this)
  private val partnerName = message.sender.select(User::nameProperty)
  private val data = message.data

  override val root = hbox {
    spacing = MainStylesheet.defaultSpacing
    addClass(MainStylesheet.chatMessage)

    run {
      imageview("user.png").avatarSize()
    }

    vbox {
      spacing = 3.0

      hbox {
        spacing = MainStylesheet.defaultSpacing

        label(partnerName).addClass(MainStylesheet.partnerName)
        label("Today at 05:14").addClass(MainStylesheet.messageTime)
      }

      // TODO: For the moment, only text is show to the user
      label(data).isWrapText = true
    }
  }
}