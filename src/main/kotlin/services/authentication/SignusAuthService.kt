package services.authentication

import models.AuthUser

class SignusAuthService(endpoint: String) : AuthService<AuthUser> {
  override val user: AuthUser?
    get() = TODO()

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