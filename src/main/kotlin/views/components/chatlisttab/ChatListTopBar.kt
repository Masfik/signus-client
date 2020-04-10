package views.components.chatlisttab

import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import org.controlsfx.glyphfont.FontAwesome
import org.controlsfx.glyphfont.Glyph
import org.controlsfx.glyphfont.GlyphFontRegistry
import tornadofx.*
import tornadofx.controlsfx.glyph
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
        button("", fontAwesome.create(FontAwesome.Glyph.USER_PLUS).color(Color.BLACK))
        button("", fontAwesome.create(FontAwesome.Glyph.GEAR).color(Color.CHARTREUSE))
      }
    }
  }
}