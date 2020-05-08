package services.api

import models.AuthUser
import models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserAPI {
  @GET("user")
  suspend fun getAuthUser(@Header("Authorization") token: String): AuthUser

  @GET("user/{username}")
  suspend fun getUser(
    @Header("Authorization") token: String,
    @Path("username") username: String
  ): User?

  @GET("user/chats") // TODO: this implementation is WIP
  suspend fun chatList(@Header("Authorization") token: String): List<User>?
}