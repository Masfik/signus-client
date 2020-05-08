package services.chat.updates

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import models.User
import java.time.LocalDateTime
import java.time.ZoneOffset

@JsonClass(generateAdapter = true)
data class MessageUpdate(
  val chatId: String,
  val data: String, // TODO: only text is supported for now
  val dateTime: LocalDateTime,
  val messageId: String? = null,
  val from: String? = null // TODO: in case group chats are ever implemented
)

class MessageUpdateAdapter {
  @FromJson
  fun dateTimeFromJson(dateTime: Long): LocalDateTime = LocalDateTime.parse(dateTime.toString())

  @ToJson
  fun dateTimeToJson(dateTime: LocalDateTime): Long = dateTime.toEpochSecond(ZoneOffset.UTC)
}
