package models

import tornadofx.ItemViewModel

data class Chat(val id: Int, val partner: User)

class ChatModel : ItemViewModel<Chat>() {

}