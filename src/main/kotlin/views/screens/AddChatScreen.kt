package views.screens

import controllers.AddChatController
import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.controlsfx.glyphfont.FontAwesome.Glyph
import tornadofx.*
import views.stylesheets.MainStylesheet.Companion.fontAwesome
import views.stylesheets.MainStylesheet.Companion.topBar

class AddChatScreen : View() {
  private val controller: AddChatController by inject()
  private var username: TextField by singleAssign()

  override val root = borderpane {
    setPrefSize(300.0, 200.0)

    top {
      stackpane {
        addClass(topBar)

        vbox {
          hbox {
            username = textfield {
              hgrow = Priority.ALWAYS
              promptText = "Search by username"
            }
            button("", fontAwesome.create(Glyph.SEARCH)) {
              isDefaultButton = true
              action {
                GlobalScope.launch(Dispatchers.IO) {
                  controller.search(username)
                }
              }
            }
          }
        }
      }
    }

    center {
      stackpane {
        label(controller.userFoundProperty)
      }
    }
  }
}