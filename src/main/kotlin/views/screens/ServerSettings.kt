package views.screens

import controllers.LoginController
import tornadofx.*

class ServerSettings : View("Server Settings") {
  private val controller: LoginController by inject()

  override val root = form {
    fieldset("Server Endpoint") {
      textfield(controller.endpointProperty)
    }
    button("Save") {
      useMaxWidth = true
      action { close() }
    }
  }
}
