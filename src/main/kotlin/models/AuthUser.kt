package models

import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.toModel
import javax.json.JsonObject

class AuthUser(name: String, username: String, email: String) : User(name, username, email) {
  val chatsProperty = SimpleListProperty<Chat>(FXCollections.observableArrayList(ArrayList()))
  val chats: ObservableList<Chat> by chatsProperty

  val contactsProperty = SimpleListProperty<User>(FXCollections.observableArrayList(ArrayList()))
  val contacts: ObservableList<User> by contactsProperty

  override fun updateModel(json: JsonObject) {
    super.updateModel(json)
    with(json) {
      chats.setAll(getJsonArray("chats").toModel())
      contacts.setAll(getJsonArray("users").toModel())
    }
  }
}

class AuthUserModel(authUser: AuthUser? = null) : ItemViewModel<AuthUser>(authUser) {
  val name = bind(AuthUser::nameProperty)
  val username = bind(AuthUser::usernameProperty)
  val email = bind(AuthUser::emailProperty)
  val status = bind(AuthUser::statusProperty)
  val chats = bind(AuthUser::chatsProperty)
  val contacts = bind(AuthUser::contactsProperty)
}