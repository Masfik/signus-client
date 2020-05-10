package services.api

import retrofit2.http.Body
import retrofit2.http.POST
import services.api.adapters.LoginDetails
import services.api.adapters.LoginResult
import services.api.adapters.RegisterDetails

interface AuthAPI {
  @POST("login")
  suspend fun login(@Body loginData: LoginDetails): LoginResult

  @POST("register")
  suspend fun register(@Body registerDetails: RegisterDetails): LoginResult
}