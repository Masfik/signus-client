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
  val id: Int?,
  val messages: List<Message>
)

class ChatAdapter {
  @FromJson fun fromJson(chatJson: ChatJson): Chat = Chat(chatJson.recipient, chatJson.id, chatJson.messages)

  @ToJson fun toJson(chat: Chat): ChatJson = ChatJson(chat.recipient, chat.id, chat.messageList)
}