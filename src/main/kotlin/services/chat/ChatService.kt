package services.chat

import com.tinder.scarlet.websocket.WebSocketEvent
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow
import models.Chat
import services.chat.updates.MessageUpdate
import services.chat.updates.UserStatusUpdate
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

//----------------------------------------------------------------------------------------------------------------------
// MESSAGE-RELATED
//----------------------------------------------------------------------------------------------------------------------

  /**
   * Send a chat message to the currently connected WebSocket.
   * The [MessageUpdate] will need to point to the [Chat] ID of which the message is related to.
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

//----------------------------------------------------------------------------------------------------------------------
// USER-RELATED
//----------------------------------------------------------------------------------------------------------------------

  /**
   * Observe user updates.
   * The update collected can be either for the [AuthUser], or their conversation partners ([User]) within the list of
   * chats.
   */
  @Receive
  fun observeUser(): Flow<UserUpdate>

  /**
   * Observe user status updates.
   * The update collected will contain a [userId] field that that indicates which [User] the new status will apply to.
   *
   * Example: { "userId": "5eab669ca82e9f0790a5faca", "status": "ONLINE" }
   */
  @Receive
  fun observeUserStatus(): Flow<UserStatusUpdate>
}