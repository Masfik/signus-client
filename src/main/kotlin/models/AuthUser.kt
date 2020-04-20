package models

import javafx.beans.property.Property
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.toModel
import javax.json.JsonObject
import tornadofx.setValue

class AuthUser(id: Int? = null, name: String, username: String, email: String) : User(id, name, username, email) {
  val chatsProperty = SimpleListProperty<Chat>(FXCollections.observableArrayList(ArrayList()))
  val chats: ObservableList<Chat> by chatsProperty

  val activeChatProperty = SimpleObjectProperty<Chat>()
  var activeChat: Chat by activeChatProperty

  override fun updateModel(json: JsonObject) {
    super.updateModel(json)
    with(json) {
      chats.setAll(getJsonArray("chats").toModel())
    }
  }
}

class AuthUserModel : ItemViewModel<AuthUser>() {
  val id: Property<Int> = bind(AuthUser::id)
  val firstName: SimpleStringProperty = bind(AuthUser::firstNameProperty)
  val lastName: SimpleStringProperty = bind(AuthUser::lastNameProperty)
  val username: SimpleStringProperty = bind(AuthUser::usernameProperty)
  val email: SimpleStringProperty = bind(AuthUser::emailProperty)
  val status: SimpleObjectProperty<UserStatus> = bind(AuthUser::statusProperty)
  val chats: SimpleListProperty<Chat> = bind(AuthUser::chatsProperty)
  val activeChat: SimpleObjectProperty<Chat> = bind(AuthUser::activeChatProperty)
}