package models

import javafx.beans.property.*
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*

class Chat(id: Int, partner: User, messageList: List<Message> = ArrayList()) : JsonModel {
  val idProperty = SimpleIntegerProperty(id)
  val id: Int by idProperty

  val partnerProperty = SimpleObjectProperty(partner)
  val partner: User by partnerProperty

  val messagesProperty = SimpleListProperty(FXCollections.observableArrayList(messageList))
  val messageList: ObservableList<Message> by messagesProperty

  val preview: String
    get() = if (messageList.isNotEmpty()) {
      "${messageList.last().preview}"
    } else "No messages"
}

class ChatModel : ItemViewModel<Chat>() {
  val id: SimpleIntegerProperty = bind(Chat::idProperty)
  val partner: SimpleObjectProperty<User> = bind(Chat::partnerProperty)
  val messageList: SimpleListProperty<Message> = bind(Chat::messagesProperty)
  val preview = stringBinding(messageList) { item?.preview }
}
