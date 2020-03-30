package models

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class User(username: String, email: String): JsonModel {
    private var state: UserState? = null
    private var contacts: List<User>? = null

    val nameProperty = SimpleStringProperty()
    var name: String? by nameProperty


    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name")
        }
    }
}

class UserModel: ItemViewModel<User>() {
    val name = bind(User::nameProperty)
}