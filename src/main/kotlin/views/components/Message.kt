package views.components

import tornadofx.*
import views.MainStylesheet
import views.MainStylesheet.Companion.avatarSize

class Message : Fragment() {
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

        label("Name Surname").addClass(MainStylesheet.partnerName)
        label("Today at 05:14").addClass(MainStylesheet.messageTime)
      }

      label("Gagaggaga").isWrapText = true
    }
  }
}