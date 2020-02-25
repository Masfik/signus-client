package us.groupwork.mta.signus.models

class Message<T>(val chat: Chat, val id: Int, data: T, sender: User, recipient: User) {
    private lateinit var state: MessageState
}