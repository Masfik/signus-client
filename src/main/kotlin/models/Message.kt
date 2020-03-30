package models

class Message<T>(val chat: Chat, val id: Int, var data: T, sender: User, recipient: User) {
  var state: MessageState = MessageState.SENT
}