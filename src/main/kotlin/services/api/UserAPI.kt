package services.api

import models.AuthUser
import models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserAPI {
  @GET("user")
  fun getUser(@Header("Authorization") token: String): Call<AuthUser>

  @GET("user/search/{username}")
  fun search(
    @Header("Authorization") token: String,
    @Path("username") username: String
  ): Call<User?>

  @GET("user/chats")
  fun chatList(@Header("Authorization") token: String): Call<List<User>?>
}