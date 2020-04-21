package models

import javafx.beans.binding.StringBinding
import javafx.beans.property.Property
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

open class User(
  firstName: String, username: String, email: String, var id: Int? = null, lastName: String? = null
) : JsonModel {
  val firstNameProperty = SimpleStringProperty(firstName)
  var firstName: String? by firstNameProperty

  val lastNameProperty = SimpleStringProperty(lastName)
  var lastName: String? by lastNameProperty

  // Automatically adapts when either properties change.
  val fullNameProperty = stringBinding(firstNameProperty, lastNameProperty) {
    if (lastName == null) firstName
    else "${this@User.firstName} ${this@User.lastName}"
  }
  val fullName: String by fullNameProperty

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
  val lastName: SimpleStringProperty = bind(User::lastNameProperty)
  val fullName: Property<StringBinding> = bind(User::fullNameProperty)
  val username: SimpleStringProperty = bind(User::usernameProperty)
  val email: SimpleStringProperty = bind(User::emailProperty)
  val status: SimpleObjectProperty<UserStatus> = bind(User::statusProperty)
}
