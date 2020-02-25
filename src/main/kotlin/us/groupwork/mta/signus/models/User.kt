package us.groupwork.mta.signus.models

class User(username: String, password: String, email: String) {
    private lateinit var state: String
    private lateinit var contacts: List<User>

}