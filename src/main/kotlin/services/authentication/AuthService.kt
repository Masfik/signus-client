package services.authentication

interface AuthService<U> {
  /**
   * Obtain the user of type U if available and valid.
   * null is returned if the user is unavailable or invalid.
   */
  val user: U? // TODO: Might change the type later and use some sort of stream

  /**
   * Sign in with the provided identifier and password.
   * The identifier can be either e-mail or username depending on the authentication provider.
   */
  fun login(identifier: String, password: String): U?

  /**
   * Register with the provided identifier and password.
   * The identifier can be either e-mail or username depending on the authentication provider.
   */
  fun register(username: String, password: String): U?

  /**
   * Sign out of the services.
   */
  fun logout()
}