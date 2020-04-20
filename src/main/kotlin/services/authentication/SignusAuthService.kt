package services.authentication

import models.AuthUser

object SignusAuthService : AuthService<AuthUser> {
  override val user: AuthUser?
    get() = TODO("Not yet implemented")

  override fun login(identifier: String, password: String): AuthUser? {
    TODO("Not yet implemented")
  }

  override fun register(username: String, password: String): AuthUser? {
    TODO("Not yet implemented")
  }

  override fun logout() {
    TODO("Not yet implemented")
  }
}