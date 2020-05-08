package services.authentication

interface AuthService<T> {
  /**
   * Obtain the user of type T if available and valid.
   * null is returned if the user is unavailable or invalid.
   */
  val user: T? // TODO: Might change the type later and use some sort of stream

  /**
   * Sign in with the provided identifier and password.
   * The identifier can be either e-mail or username depending on the authentication provider.
   */
  fun login(identifier: String, password: String): T?

  /**
   * Register with the provided identifier and password.
   * The identifier can be either e-mail or username depending on the authentication provider.
   */
  fun register(username: String, password: String): T?

  /**
   * Sign out of the services.
   */
  fun logout()
}