package views.components.loginscreen

import controllers.LoginController
import org.controlsfx.control.PopOver
import org.controlsfx.glyphfont.FontAwesome.Glyph.GEAR
import tornadofx.*
import tornadofx.controlsfx.popover
import tornadofx.controlsfx.showPopover
import views.stylesheets.MainStylesheet.Companion.fontAwesome

class LoginTopBar : View() {
  private val controller: LoginController by inject()

  override val root = button("SERVER SETTINGS", fontAwesome.create(GEAR)) {
    popover {
      isDetachable = false

      form {
        fieldset("Server Endpoint") {
          textfield(controller.endpointProperty)
        }
        button("Save") {
          useMaxWidth = true
          isDefaultButton = true
          action { this@popover.hide() }
        }
      }
    }
    action { showPopover() }
  }
}
