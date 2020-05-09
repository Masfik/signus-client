package services.api

import models.AuthUser
import models.User
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserAPI {
  @GET("user")
  suspend fun authUser(@Header("Authorization") token: String): AuthUser

  @GET("user")
  suspend fun userByUsername(
    @Header("Authorization") token: String,
    @Query("username") username: String
  ): User?

  @GET("user")
  suspend fun userById(
    @Header("Authorization") token: String,
    @Query("id") id: String
  ): User?
}