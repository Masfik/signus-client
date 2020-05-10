package models.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import models.Chat
import models.Message
import models.User

@JsonClass(generateAdapter = true)
data class ChatJson(
  val recipient: User,
  val id: String?,
  val messages: List<Message>? = ArrayList()
)

class ChatAdapter {
  @FromJson
  fun fromJson(chatJson: ChatJson): Chat = with(chatJson) {
    Chat(recipient, id, messages ?: ArrayList())
  }

  @ToJson
  fun toJson(chat: Chat): ChatJson = ChatJson(chat.recipient, chat.id, chat.messageList)
}