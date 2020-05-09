import kotlinx.coroutines.*
import services.chat.SignusAppLifecycle
import tornadofx.App
import tornadofx.launch
import views.screens.LoginScreen
import views.stylesheets.MainStylesheet

class Signus : App(LoginScreen::class, MainStylesheet::class) {
  override fun stop() {
    super.stop()
    SignusAppLifecycle.close()  // Close active WebSocket connections
  }
}

fun main() = runBlocking { launch<Signus>() }