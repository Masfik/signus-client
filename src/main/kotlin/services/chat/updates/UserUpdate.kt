package services.chat.updates

import com.squareup.moshi.JsonClass
import models.User

enum class UserUpdateType { AUTH_USER, USER }

@JsonClass(generateAdapter = true)
data class UserUpdate(
  val type: UserUpdateType,
  val user: User
)