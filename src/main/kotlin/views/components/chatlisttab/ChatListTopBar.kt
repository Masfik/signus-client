package views.components.chatlisttab

import javafx.scene.layout.Priority
import models.AuthUserModel
import org.controlsfx.glyphfont.FontAwesome.Glyph
import tornadofx.*
import tornadofx.controlsfx.popover
import tornadofx.controlsfx.showPopover
import views.stylesheets.MainStylesheet
import views.stylesheets.MainStylesheet.Companion.fontAwesome

class ChatListTopBar : View() {
  private val authUser: AuthUserModel by inject()

  companion object {
    val settingsPopOver by cssid()
  }

  override val root = stackpane {
    addClass(MainStylesheet.topBar)

    vbox {
      hbox {
        textfield {
          hgrow = Priority.ALWAYS
          promptText = "Search"
        }
        button("", fontAwesome.create(Glyph.GEAR)) {
          tooltip("Settings ${runLater { authUser.username.value }}")
          popover {
            title = "Settings"
            isHeaderAlwaysVisible = true
            find<SettingsPopOver>().root
          }
          action { showPopover() }
        }
      }
    }
  }
}