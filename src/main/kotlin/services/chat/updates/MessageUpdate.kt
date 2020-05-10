package services.chat.updates

import com.squareup.moshi.JsonClass
import misc.Date
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class MessageUpdate(
  val chatId: String,
  val data: String, // TODO: only text is supported for now
  @Date val dateTime: LocalDateTime,
  val messageId: String? = null,
  val from: String? = null // TODO: in case group chats are ever implemented
)