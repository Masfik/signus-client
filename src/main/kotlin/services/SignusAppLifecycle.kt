package services

import com.tinder.scarlet.Lifecycle
import com.tinder.scarlet.LifecycleState
import com.tinder.scarlet.lifecycle.LifecycleRegistry
import javafx.beans.property.SimpleObjectProperty
import tornadofx.getValue
import tornadofx.onChange
import tornadofx.setValue

/**
 * The Lifecycle of the app needed by Scarlet to close/open WebSocket connections according to certain criteria.
 * For example, when closing the app altogether or when the authentication status is LOGGED_OUT (WIP).
 */
class SignusAppLifecycle(
  lifecycleRegistry: LifecycleRegistry = LifecycleRegistry()
) : Lifecycle by lifecycleRegistry {
  companion object {
    private val statusProperty = SimpleObjectProperty<AppState>()
    private var status: AppState by statusProperty

    /**
     * Marks the AppState as CLOSED. This will trigger the Lifecycle registry to stop and gracefully close currently
     * active WebSockets.
     */
    fun close() { status = AppState.CLOSED }
  }

  init {
    // Initial Started state
    lifecycleRegistry.onNext(LifecycleState.Started)

    statusProperty.onChange {
      when (it) {
        AppState.OPEN -> lifecycleRegistry.onNext(LifecycleState.Started)
        AppState.CLOSED -> lifecycleRegistry.onNext(LifecycleState.Stopped)
      }
    }
  }

  enum class AppState {
    OPEN,
    CLOSED
  }

  // TODO: add authentication status to lifecycle
  enum class AuthStatus {
    LOGGED_IN,
    LOGGED_OUT
  }
}