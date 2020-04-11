package views.components.chatlisttab

import javafx.geometry.Pos
import javafx.scene.layout.Priority
import org.controlsfx.glyphfont.FontAwesome.Glyph
import tornadofx.*
import views.stylesheets.MainStylesheet
import views.stylesheets.MainStylesheet.Companion.defaultSpacing
import views.stylesheets.MainStylesheet.Companion.fontAwesome

class ChatListTopBar : View() {
  override val root = stackpane {
    addClass(MainStylesheet.topBar)

    vbox {
      alignment = Pos.CENTER_LEFT

      hbox {
        spacing = defaultSpacing

        textfield {
          hgrow = Priority.ALWAYS
          promptText = "Search"
        }
        button("", fontAwesome.create(Glyph.USER_PLUS))
        button("", fontAwesome.create(Glyph.GEAR))
      }
    }
  }
}