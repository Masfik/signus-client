package models

import javafx.beans.property.Property
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Message(var id: Int? = null, data: Any, sender: User, dateTime: LocalDateTime = LocalDateTime.now()) {
  val dataProperty = SimpleObjectProperty(data)
  var data: Any by dataProperty

  val senderProperty = SimpleObjectProperty(sender)
  var sender: User by senderProperty

  val statusProperty = SimpleObjectProperty(MessageStatus.SENT)
  var status: MessageStatus by statusProperty

  val dateTimeProperty = SimpleObjectProperty(dateTime)
  val dateTime: LocalDateTime by dateTimeProperty

  val formattedDateTimeProperty = stringBinding(dateTimeProperty) {
    val now = LocalDateTime.now()
    if (dateTime.dayOfMonth == now.dayOfMonth && dateTime.month == now.month)
      "Today at ${dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
    else dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
  }
  val formattedDateTime: String by formattedDateTimeProperty

  val preview: String
    get() = when (data) {
      is String -> "$data"
      // is Image -> "Image"
      // is File -> "Document"
      else -> "Unknown"
    }
}

class MessageModel : ItemViewModel<Message>() {
  val id: Property<Int> = bind(Message::id)
  val data: SimpleObjectProperty<Any> = bind(Message::dataProperty)
  val sender: SimpleObjectProperty<User> = bind(Message::senderProperty)
  val status: SimpleObjectProperty<MessageStatus> = bind(Message::statusProperty)
  val dateTime: SimpleObjectProperty<LocalDateTime> = bind(Message::dateTimeProperty)
  val formattedDateTime = bind(Message::formattedDateTimeProperty)
}
