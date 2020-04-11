package views.components.chatlisttab

import javafx.scene.layout.Priority
import org.controlsfx.glyphfont.FontAwesome.Glyph
import tornadofx.*
import views.screens.AddContact
import views.stylesheets.MainStylesheet
import views.stylesheets.MainStylesheet.Companion.fontAwesome

class ChatListTopBar : View() {
  override val root = stackpane {
    addClass(MainStylesheet.topBar)

    vbox {
      hbox {
        textfield {
          hgrow = Priority.ALWAYS
          promptText = "Search"
        }
        button("", fontAwesome.create(Glyph.USER_PLUS)).action {
         find<AddContact>().openModal()
        }
        button("", fontAwesome.create(Glyph.GEAR))
      }
    }
  }
}