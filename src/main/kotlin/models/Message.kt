package models

import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue

class Message(id: Int, data: Any, sender: User) {
  val idProperty = SimpleIntegerProperty(id)
  val id by idProperty

  val dataProperty = SimpleObjectProperty(data)
  var data: Any by dataProperty

  val senderProperty = SimpleObjectProperty(sender)
  var sender: User by senderProperty

  val statusProperty = SimpleObjectProperty(MessageStatus.SENT)
  var status: MessageStatus by statusProperty

  val messagePreview: String = when (data) {
    is String -> data as String
    else -> "Unknown"
  }
}

class MessageModel : ItemViewModel<Message>() {
  val id: SimpleIntegerProperty = bind(Message::idProperty)
  val data: SimpleObjectProperty<Any> = bind(Message::dataProperty)
  val sender: SimpleObjectProperty<User> = bind(Message::senderProperty)
  val status: SimpleObjectProperty<MessageStatus> = bind(Message::statusProperty)
  val messagePreview: Property<String> = bind(Message::messagePreview)
}
