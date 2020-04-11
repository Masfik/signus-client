package models

import javafx.beans.binding.StringBinding
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue
import tornadofx.stringBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
  val dateTime: LocalDateTime by dateTimeProperty

  val formattedDateTime: String
    get() {
      val now = LocalDateTime.now()
      return if (dateTime.dayOfMonth == now.dayOfMonth && dateTime.month == now.month)
        "Today at ${dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
      else dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    }

  val preview: String
    get() = when (data) {
      is String -> "$data"
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
  val formattedDateTime: StringBinding = stringBinding(dateTime) { item?.formattedDateTime }
}
