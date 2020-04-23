package services

import com.tinder.scarlet.Message
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.channels.ReceiveChannel
import models.Chat
import models.User

interface ChatService {
  @Receive
  fun observeWebSocketEvent(): ReceiveChannel<WebSocket.Event>

  /* * * * * * * *\
  * Chats-related *
  \* * * * * * * */

  @Receive
  fun observeChats(): ReceiveChannel<Chat>

  /* * * * * * * * *\
  * Message-related *
  \* * * * * * * * */

  @Send
  fun sendMessage(chat: Chat, message: Message)

  @Receive
  fun observeMessages(): ReceiveChannel<Message>

  @Receive
  fun observeMessageStatus()

  /* * * * * * * *\
  * Users-related *
  \* * * * * * * */

  @Receive
  fun observeUsers(): ReceiveChannel<User>
}