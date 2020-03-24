package us.groupwork.mta.signus.models

class Message<T>(val chat: Chat, val id: Int, var data: T, sender: User, recipient: User) {
    lateinit var state: MessageState
}