package models

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class User(name: String, username: String, email: String) : JsonModel {
  var state: UserState = UserState.OFFLINE
  var chats: List<Chat> = ArrayList()
  var contacts: List<User> = ArrayList()

  val nameProperty = SimpleStringProperty(this, "name", name)
  var name: String? by nameProperty

  override fun updateModel(json: JsonObject) {
    with(json) {
      name = string("name")
    }
  }
}

class UserModel : ItemViewModel<User>() {
  val name = bind(User::nameProperty)
}