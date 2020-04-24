package services.chat.updates

import models.Message

data class MessageUpdate(val chatId: Int, val message: Message)