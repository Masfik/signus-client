package services.chat.updates

import models.User

enum class UserUpdateType { AUTH_USER, USER }

data class UserUpdate(
  val updateType: UserUpdateType,
  val user: User
)