package models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue

class Chat(id: Int, partner: User) {
  val idProperty = SimpleIntegerProperty(id)
  val id: Int by idProperty

  val partnerProperty = SimpleObjectProperty(partner)
  var partner by partnerProperty

  var messages: List<Message> = ArrayList()
}

class ChatModel : ItemViewModel<Chat>() {
  val partner = bind(Chat::partnerProperty)
  val id = bind(Chat::idProperty)
}
