package models

import javafx.beans.property.Property
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Message(data: Any, sender: User, var id: String? = null, dateTime: LocalDateTime = LocalDateTime.now()) {
  // Data (the content sent)
  val dataProperty = SimpleObjectProperty(data)
  var data: Any by dataProperty

  // Sender
  val senderProperty = SimpleObjectProperty(sender)
  var sender: User by senderProperty

  // Status (SENT, UNSENT, READ)
  val statusProperty = SimpleObjectProperty(MessageStatus.SENT)
  var status: MessageStatus by statusProperty

  // Date and Time
  val dateTimeProperty = SimpleObjectProperty(dateTime)
  val dateTime: LocalDateTime by dateTimeProperty

  // Formatted date and time
  val formattedDateTimeProperty = stringBinding(dateTimeProperty) {
    val now = LocalDateTime.now()

    if (dateTime.dayOfMonth == now.dayOfMonth && dateTime.month == now.month)
      "Today at ${dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
    else dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
  }
  val formattedDateTime: String by formattedDateTimeProperty

  // Preview
  val previewProperty = stringBinding(dataProperty) {
    when (data) {
      is String -> "$data"
      // is Image -> "Image"
      // is File -> "Document"
      else -> "Unknown"
    }
  }
  val preview: String by previewProperty
}

class MessageModel : ItemViewModel<Message>() {
  val id: Property<String> = bind(Message::id)
  val data: SimpleObjectProperty<Any> = bind(Message::dataProperty)
  val sender: SimpleObjectProperty<User> = bind(Message::senderProperty)
  val status: SimpleObjectProperty<MessageStatus> = bind(Message::statusProperty)
  val dateTime: SimpleObjectProperty<LocalDateTime> = bind(Message::dateTimeProperty)
  val formattedDateTime = bind(Message::formattedDateTimeProperty)
  val preview = bind(Message::previewProperty)
}
