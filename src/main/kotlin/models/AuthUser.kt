package models

import javafx.beans.binding.StringBinding
import javafx.beans.property.*
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ItemViewModel
import tornadofx.getValue
import javax.json.JsonObject
import tornadofx.setValue

class AuthUser(
  firstName: String,
  username: String,
  email: String,
  id: String? = null,
  lastName: String? = "",
  token: String? = null
) : User(firstName, username, email, id, lastName) {
  // Chats
  val chatsProperty = SimpleListProperty<Chat>(FXCollections.observableArrayList(ArrayList()))
  val chats: ObservableList<Chat> by chatsProperty

  // The active chat currently selected from the list in the sidebar
  val activeChatProperty = SimpleObjectProperty<Chat>()
  var activeChat: Chat by activeChatProperty

  val tokenProperty = SimpleStringProperty(token)
  var token: String? by tokenProperty;

  override fun updateModel(json: JsonObject) {
    super.updateModel(json)
    with(json) {
      /*chats.setAll(getJsonArray("chats").toModel())*/
    }
  }
}

class AuthUserModel : ItemViewModel<AuthUser>() {
  val id: ReadOnlyProperty<String> = bind(AuthUser::id)
  val firstName: SimpleStringProperty = bind(AuthUser::firstNameProperty)
  val lastName: SimpleStringProperty = bind(AuthUser::lastNameProperty)
  val fullName: Property<StringBinding> = bind(AuthUser::fullNameProperty)
  val username: SimpleStringProperty = bind(AuthUser::usernameProperty)
  val email: SimpleStringProperty = bind(AuthUser::emailProperty)
  val status: SimpleObjectProperty<UserStatus> = bind(AuthUser::statusProperty)
  val chats: SimpleListProperty<Chat> = bind(AuthUser::chatsProperty)
  val activeChat: SimpleObjectProperty<Chat> = bind(AuthUser::activeChatProperty)
  var token: SimpleStringProperty = bind(AuthUser::tokenProperty)
}