package views.components.loginscreen

import models.ServerSettingsModel
import org.controlsfx.glyphfont.FontAwesome.Glyph.GEAR
import tornadofx.*
import tornadofx.controlsfx.popover
import tornadofx.controlsfx.showPopover
import views.stylesheets.MainStylesheet.Companion.fontAwesome

class LoginTopBar : View() {
  private val serverSettings: ServerSettingsModel by inject()

  override val root = button("SERVER SETTINGS", fontAwesome.create(GEAR)) {
    popover {
      isDetachable = false

      form {
        fieldset("Server Endpoint") {
          textfield(serverSettings.baseEndpoint).required()
        }
        fieldset {
          checkbox("Use HTTPS (requires a domain name)", serverSettings.useHttps)
        }
        button("Save") {
          useMaxWidth = true
          isDefaultButton = true

          enableWhen(serverSettings.valid)
          action {
            serverSettings.commit()
            this@popover.hide()
          }
        }
      }
    }
    action { showPopover() }
  }
}
