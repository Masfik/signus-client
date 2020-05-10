package models.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import models.Message
import models.User
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class MessageJson(
  val id: String?,
  val data: String, // TODO: might change later
  val sender: User,
  val dateTime: String
)

class MessageAdapter {
  @FromJson
  fun fromJson(messageJson: MessageJson): Message = with(messageJson) {
    Message(data, sender, LocalDateTime.parse(dateTime))
  }

  @ToJson
  fun toJson(message: Message): MessageJson = with(message) {
    MessageJson(id, data as String, sender, dateTime.toString())
  }
}