package views.screens

import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import org.controlsfx.glyphfont.FontAwesome.Glyph
import tornadofx.*
import views.stylesheets.MainStylesheet.Companion.fontAwesome
import views.stylesheets.MainStylesheet.Companion.topBar

class AddContact : View("Add Contact") {
  private var username: TextField by singleAssign()

  override val root = borderpane {
    setPrefSize(400.0, 200.0)

    top {
      stackpane {
        addClass(topBar)
        vbox {
          hbox {
            username = textfield {
              hgrow = Priority.ALWAYS
              promptText = "Username of the user"
            }
            button("", fontAwesome.create(Glyph.SEARCH))
          }
        }
      }
    }

    center {
      stackpane {
        label("No users found!")
      }
    }
  }
}