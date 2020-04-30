package models.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import models.User

@JsonClass(generateAdapter = true)
data class UserJson(
  val id: Int?,
  val firstName: String,
  val lastName: String,
  val username: String,
  val email: String
)

class UserAdapter {
  @FromJson fun fromJson(userJson: UserJson): User = with(userJson) {
    User(firstName, username, email, id, lastName)
  }

  @ToJson fun toJson(user: User) = with(user) {
    UserJson(id, firstName!!, lastName!!, username!!, email!!)
  }
}