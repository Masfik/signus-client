package models

class Message(val chat: Chat, val id: Int, var data: Any, sender: User, recipient: User) {
  var state: MessageState = MessageState.SENT

  fun dataAsString() = when(data) {
    is String -> data as String
    else -> "Unknown"
  }
}