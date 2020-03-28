package models

class User(username: String, email: String) {
  lateinit var state: UserState
  lateinit var contacts: List<User>
}