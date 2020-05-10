package models

import javafx.beans.binding.StringBinding
import javafx.beans.property.Property
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

open class User(
  firstName: String,
  username: String,
  email: String,
  var id: String? = null,
  lastName: String? = null,
  status: UserStatus = UserStatus.OFFLINE
) {
  // First name
  val firstNameProperty = SimpleStringProperty(firstName)
  var firstName: String? by firstNameProperty

  // Last Name
  val lastNameProperty = SimpleStringProperty(lastName)
  var lastName: String? by lastNameProperty

  // Full name: automatically adapts when either names change.
  val fullNameProperty = stringBinding(firstNameProperty, lastNameProperty) {
    if (lastName == null) firstName
    else "${this@User.firstName} ${this@User.lastName}"
  }
  val fullName: String by fullNameProperty

  // Username
  val usernameProperty = SimpleStringProperty(username)
  var username: String? by usernameProperty

  // Email
  val emailProperty = SimpleStringProperty(email)
  var email: String? by emailProperty

  // Status (ONLINE, OFFLINE, BUSY)
  val statusProperty = SimpleObjectProperty(status)
  var status: UserStatus by statusProperty

  // Avatar
  val avatarProperty = SimpleStringProperty("anon-user.png")
  open var avatar: String by avatarProperty
}

class UserModel : ItemViewModel<User>() {
  val id: ReadOnlyProperty<String> = bind(User::id)
  val firstName: SimpleStringProperty = bind(User::firstNameProperty)
  val lastName: SimpleStringProperty = bind(User::lastNameProperty)
  val fullName: Property<StringBinding> = bind(User::fullNameProperty)
  val username: SimpleStringProperty = bind(User::usernameProperty)
  val email: SimpleStringProperty = bind(User::emailProperty)
  val status: SimpleObjectProperty<UserStatus> = bind(User::statusProperty)
  val avatar: SimpleStringProperty = bind(User::avatar)
}
