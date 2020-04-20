package services.api

import models.AuthUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthAPI {
  @GET("login/{identifier}/{password}")
  fun login(
    @Path("identifier") identifier: String,
    @Path("password") password: String
  ): Call<AuthUser?>

  @GET("register/{identifier}/{password}")
  fun register(
    @Path("identifier") identifier: String,
    @Path("password") password: String
  ): Call<AuthUser?>
}