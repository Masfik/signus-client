package services.chat

import com.tinder.scarlet.websocket.WebSocketEvent
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow
import models.Chat
import services.chat.updates.MessageUpdate
import services.chat.updates.UserUpdate

interface ChatService {
  /**
   * Observe WebSocket events.
   * Whenever a certain event occurs to the WebSocket connection, the flow will notify accordingly.
   *
   * Example: OnConnectionOpened, OnConnectionClosed, OnConnectionFailed
   */
  @Receive
  fun observeWebSocketEvent(): Flow<WebSocketEvent>

  /* * * * * * * *\
  * Chats-related *
  \* * * * * * * */

  /**
   * Observe incoming chats.
   * If a new [Chat] is detected, the flow will notify of any new addition.
   */
  @Receive
  fun observeIncomingChat(): Flow<Chat>

  /* * * * * * * * *\
  * Message-related *
  \* * * * * * * * */

  /**
   * Send a chat message to the currently connected WebSocket.
   * The [MessageUpdate] will need to point to [Chat] ID of which the message is related to.
   */
  @Send
  fun sendMessage(messageUpdate: MessageUpdate)

  /**
   * Observe incoming chat messages.
   * The [MessageUpdate] will contain the ID of the [Chat] it belongs to along with the actual content of the [Message].
   */
  @Receive
  fun observeIncomingMessage(): Flow<MessageUpdate>

  /*@Receive TODO
  fun observeMessageStatus()*/

  /* * * * * * * *\
  * Users-related *
  \* * * * * * * */

  /**
   * Observe user updates.
   * The update collected can be either for the [AuthUser], or their conversation partners ([User]) within the list of
   * chats.
   */
  @Receive
  fun observeUser(): Flow<UserUpdate>
}