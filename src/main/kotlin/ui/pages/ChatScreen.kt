package ui.pages

import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class ChatScreen : View("My View") {
  override val root = borderpane {
    top {
      stackpane {
        vbox {
          label("Name of the person") {
            style {
              fontWeight = FontWeight.BOLD
              paddingBottom = 3
            }
          }
          label("Offline")

          alignment = Pos.CENTER_LEFT
          style {
            paddingAll = 10
          }
        }

        style {
          prefHeight = Dimension(50.0, Dimension.LinearUnits.px)
          backgroundColor += Color.WHITE
        }
      }
    }

    center {
      label("No chat selected")
    }

    bottom {
      form {
        hbox {
          textfield {
            hgrow = Priority.ALWAYS
          }
          button("Send")

          spacing = 10.0
        }

        style {
          backgroundColor += Color.WHITE
        }
      }
    }
  }
}
