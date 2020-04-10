package models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue
import java.time.LocalDateTime

class Message(data: Any, sender: User, dateTime: LocalDateTime = LocalDateTime.now()) {
  val idProperty = SimpleIntegerProperty(0)
  val id by idProperty

  val dataProperty = SimpleObjectProperty(data)
  var data: Any by dataProperty

  val senderProperty = SimpleObjectProperty(sender)
  var sender: User by senderProperty

  val statusProperty = SimpleObjectProperty(MessageStatus.SENT)
  var status: MessageStatus by statusProperty

  val dateTimeProperty = SimpleObjectProperty(dateTime)
  var dateTime: LocalDateTime by dateTimeProperty

  val preview
    get() = when (data) {
      is String -> data
      // is Image -> "Image"
      // is File -> "Document"
      else -> "Unknown"
    }
}

class MessageModel : ItemViewModel<Message>() {
  val id: SimpleIntegerProperty = bind(Message::idProperty)
  val data: SimpleObjectProperty<Any> = bind(Message::dataProperty)
  val sender: SimpleObjectProperty<User> = bind(Message::senderProperty)
  val status: SimpleObjectProperty<MessageStatus> = bind(Message::statusProperty)
  val dateTime: SimpleObjectProperty<LocalDateTime> = bind(Message::dateTimeProperty)
  val messagePreview = bind(Message::preview)
}
