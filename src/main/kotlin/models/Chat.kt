package models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import tornadofx.ItemViewModel
import tornadofx.JsonModel
import tornadofx.getValue
import tornadofx.setValue

class Chat(id: Int, partner: User, messageList: List<Message> = ArrayList()) : JsonModel {
  val idProperty = SimpleIntegerProperty(id)
  val id by idProperty

  val partnerProperty = SimpleObjectProperty<User>(partner)
  val partner by partnerProperty

  val messagesProperty = SimpleListProperty(FXCollections.observableArrayList(messageList))
  val messageList by messagesProperty
}

class ChatModel(chat: Chat? = null) : ItemViewModel<Chat>(chat) {
  val id = bind(Chat::idProperty)
  val partner = bind(Chat::partnerProperty)
  val messageList = bind(Chat::messagesProperty)
}
