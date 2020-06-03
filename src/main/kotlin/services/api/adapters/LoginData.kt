package services.api.adapters

import com.squareup.moshi.JsonClass
import models.AuthUser

/**
 * This class can used to submit login data to the server.
 * The [identifier] parameter can be either username or email (it can depend on the authentication provider).
 */
@JsonClass(generateAdapter = true)
data class LoginDetails(
  val identifier: String,
  val password: String
)

/**
 * This class can be used to submit data when registering for the service.
 */
@JsonClass(generateAdapter = true)
data class RegisterDetails(
  val firstName: String,
  val lastName: String? = null,
  val email: String,
  val username: String,
  val password: String
)

/**
 * This class is used for performing a login or sign up operation. The [message] will always be present and will
 * indicate whether the credentials have been validated correctly. If yes, an AuthUser object will also be present.
 */
@JsonClass(generateAdapter = true)
data class LoginResult(
  val message: String,
  val user: AuthUser?
)