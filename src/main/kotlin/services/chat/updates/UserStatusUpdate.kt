package services.chat.updates

import com.squareup.moshi.JsonClass
import models.UserStatus

@JsonClass(generateAdapter = true)
data class UserStatusUpdate(
  val userId: String,
  val status: UserStatus
)