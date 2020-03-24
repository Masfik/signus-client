package us.groupwork.mta.signus.models

class User(username: String, email: String) {
    lateinit var state: UserState
    lateinit var contacts: List<User>
}