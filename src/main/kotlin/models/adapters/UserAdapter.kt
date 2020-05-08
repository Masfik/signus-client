package models.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import models.AuthUser
import models.User

@JsonClass(generateAdapter = true)
data class UserJson(
  val id: String?,
  val firstName: String,
  val lastName: String,
  val username: String,
  val email: String,
  val token: String? = null
)

class UserAdapter {
  @FromJson
  fun userFromJson(userJson: UserJson): User = with(userJson) {
    User(firstName, username, email, id, lastName)
  }

  @ToJson
  fun userToJson(user: User) = with(user) {
    UserJson(id, firstName!!, lastName!!, username!!, email!!)
  }
}

class AuthUserAdapter {
  @FromJson
  fun authUserFromJson(userJson: UserJson): AuthUser = with(userJson) {
    AuthUser(firstName, username, email, id, lastName, token)
  }

  @ToJson
  fun authUserToJson(authUser: AuthUser) = with(authUser) {
    UserJson(id, firstName!!, lastName!!, username!!, email!!, token!!)
  }
}