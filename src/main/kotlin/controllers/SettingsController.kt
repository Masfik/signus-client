package controllers

import models.AuthUserModel
import tornadofx.Controller

class SettingsController : Controller() {
  fun setProfilePicture() {

  }

  fun save(authUser: AuthUserModel) {
    println("Saved")
    authUser.commit()
  }
}