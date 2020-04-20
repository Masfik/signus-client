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
  // Settings title that updates whenever the name changes
  private val settingsTitle = stringBinding(authUser.firstName) { "Settings: ${this.value}" }

  override val root = stackpane {
    addClass(MainStylesheet.topBar)

    vbox {
      hbox {
        textfield {
          hgrow = Priority.ALWAYS
          promptText = "Search"
        }
        button("", fontAwesome.create(Glyph.GEAR)) {
          tooltip().textProperty().bind(settingsTitle)
          popover {
            titleProperty().bind(settingsTitle)
            isHeaderAlwaysVisible = true
            find<SettingsPopOver>().root
          }
          action { showPopover() }
        }
      }
    }
  }
}