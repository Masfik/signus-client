package models

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class ServerSettings {
  // Base Endpoint
  val baseEndpointProperty = SimpleStringProperty("signus.example.com")
  val baseEndpoint: String by baseEndpointProperty

  // UseHttps
  val useHttpsProperty = SimpleBooleanProperty(false)
  val useHttps: Boolean by useHttpsProperty
}

class ServerSettingsModel : ItemViewModel<ServerSettings>() {
  val baseEndpoint: SimpleStringProperty = bind(ServerSettings::baseEndpointProperty)
  val useHttps: SimpleBooleanProperty = bind(ServerSettings::useHttpsProperty)
}
