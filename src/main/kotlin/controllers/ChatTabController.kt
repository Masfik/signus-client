package controllers

import javafx.scene.control.TextField
import tornadofx.Controller

class ChatTabController : Controller() {
  fun submit(textField: TextField) {
    if (textField.text.isEmpty()) return

    println(textField.text)
    textField.text = ""
  }
}