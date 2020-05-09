package controllers

import com.squareup.moshi.Moshi
import models.AuthUser
import models.ServerSettingsModel
import models.User
import models.adapters.AuthUserAdapter
import models.adapters.UserAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import services.api.UserAPI
import tornadofx.Controller

class UserApiController : UserAPI, Controller() {
  private val serverSettings: ServerSettingsModel by inject()
  private val prefix = if (serverSettings.useHttps.value) "https://" else "http://"

  // Moshi (JSON encoding<->decoding)
  private val moshi = Moshi.Builder()
    .add(UserAdapter())
    .add(AuthUserAdapter())
    .build()

  // Rest API: Retrofit
  private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(prefix + serverSettings.baseEndpoint.valueSafe)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
  private val api = retrofit.create(UserAPI::class.java)

  override suspend fun authUser(token: String): AuthUser = api.authUser(token)

  override suspend fun userByUsername(token: String, username: String): User? = api.userByUsername(token, username)

  override suspend fun userById(token: String, id: String): User? = api.userById(token, id)
}