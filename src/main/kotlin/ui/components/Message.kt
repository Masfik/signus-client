package ui.components

import tornadofx.*
import ui.MainStylesheet

class Message : Fragment() {
  override val root = hbox {
    spacing = MainStylesheet.defaultSpacing
    addClass(MainStylesheet.chatMessage)

    run {
      imageview("user.png") {
        fitHeight = MainStylesheet.avatarSize
        fitWidth = MainStylesheet.avatarSize
      }
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