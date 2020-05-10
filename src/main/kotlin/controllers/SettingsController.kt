package controllers

import models.AuthUserModel
import tornadofx.Controller

class SettingsController : Controller() {
  fun setProfilePicture() {
    // TODO
  }

  fun save(authUser: AuthUserModel) {
    println("Saved")
    authUser.commit()
  }
}