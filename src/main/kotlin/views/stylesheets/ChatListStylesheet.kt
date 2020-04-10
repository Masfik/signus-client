package views.stylesheets

import tornadofx.*

class ChatListStylesheet : Stylesheet() {
  init {
    listView {
      backgroundColor += SignusColour.PRIMARY
      selectionBarText = SignusColour.TEXT_ON_SECONDARY
    }
  }
}