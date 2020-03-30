package models

import tornadofx.ItemViewModel

data class Chat(val id: Int, val partner: User) {
  var messages: List<Message<String>> = ArrayList()
}

class ChatModel : ItemViewModel<Chat>() {

}