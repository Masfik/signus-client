package models

import javafx.beans.binding.StringBinding
import javafx.beans.property.*
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*

class Chat(id: Int, partner: User, messageList: List<Message> = ArrayList()) : JsonModel {
  // ID
  val idProperty = SimpleIntegerProperty(id)
  val id: Int by idProperty

  // Recipient
  val recipientProperty = SimpleObjectProperty(partner)
  val recipient: User by recipientProperty

  // Messages
  val messagesProperty = SimpleListProperty(FXCollections.observableArrayList(messageList))
  val messageList: ObservableList<Message> by messagesProperty

  // Preview
  val previewProperty = stringBinding(messagesProperty) {
    if (this.value.isNotEmpty()) {
      val lastMessage = this.value.last()

      if (lastMessage.sender.id == recipient.id) lastMessage.preview
      else "You ➜ ${lastMessage.preview}"
    } else "No messages"
  }
  val preview: String by previewProperty
}

class ChatModel : ItemViewModel<Chat>() {
  val id: SimpleIntegerProperty = bind(Chat::idProperty)
  val recipient: SimpleObjectProperty<User> = bind(Chat::recipientProperty)
  val messageList: SimpleListProperty<Message> = bind(Chat::messagesProperty)
  val preview: Property<StringBinding> = bind(Chat::previewProperty)
}
