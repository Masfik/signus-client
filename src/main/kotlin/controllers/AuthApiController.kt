package controllers

import com.squareup.moshi.Moshi
import misc.UserStatusAdapter
import models.ServerSettingsModel
import models.adapters.AuthUserAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import services.api.AuthAPI
import services.api.adapters.LoginDetails
import services.api.adapters.LoginResult
import services.api.adapters.RegisterDetails
import tornadofx.Controller

class AuthApiController : AuthAPI, Controller() {
  private val serverSettings: ServerSettingsModel by inject()
  private val prefix = if (serverSettings.useHttps.value) "https://" else "http://"

  // Moshi (JSON encoding<->decoding)
  private val moshi = Moshi.Builder()
    .add(UserStatusAdapter())
    .add(AuthUserAdapter())
    .build()

  // Rest API: Retrofit
  private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(prefix + serverSettings.baseEndpoint.valueSafe)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
  private val api: AuthAPI = retrofit.create(AuthAPI::class.java)

  override suspend fun login(loginData: LoginDetails): LoginResult = api.login(loginData)

  override suspend fun register(registerDetails: RegisterDetails): LoginResult = api.register(registerDetails)
}