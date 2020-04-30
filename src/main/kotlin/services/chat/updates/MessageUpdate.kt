package services.chat.updates

import com.squareup.moshi.JsonClass
import models.Message

@JsonClass(generateAdapter = true)
data class MessageUpdate(val chatId: Int, val message: Message)