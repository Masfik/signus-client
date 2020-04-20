package models

import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject
import tornadofx.getValue
import tornadofx.setValue

open class User(var id: Int? = null, name: String, username: String, email: String) : JsonModel {
  val firstNameProperty = SimpleStringProperty(name)
  var firstName: String? by firstNameProperty

  val lastNameProperty = SimpleStringProperty()
  var lastName: String? by lastNameProperty

  val usernameProperty = SimpleStringProperty(username)
  var username: String? by usernameProperty

  val emailProperty = SimpleStringProperty(email)
  var email: String? by emailProperty

  val statusProperty = SimpleObjectProperty(UserStatus.OFFLINE)
  var status: UserStatus by statusProperty

  override fun updateModel(json: JsonObject) {
    with(json) {
      firstName = string("firstName")
      lastName = string("lastName")
      username = string("username")
      email = string("email")
    }
  }
}

open class UserModel : ItemViewModel<User>() {
  val id: ReadOnlyProperty<Int> = bind(User::id)
  val firstName: SimpleStringProperty = bind(User::firstNameProperty)
  val username: SimpleStringProperty = bind(User::usernameProperty)
  val email: SimpleStringProperty = bind(User::emailProperty)
  val status: SimpleObjectProperty<UserStatus> = bind(User::statusProperty)
}
