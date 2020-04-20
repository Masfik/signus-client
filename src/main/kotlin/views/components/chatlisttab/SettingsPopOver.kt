package views.components.chatlisttab

import controllers.SettingsController
import javafx.geometry.Pos
import models.AuthUserModel
import tornadofx.*
import views.stylesheets.MainStylesheet.Companion.avatarSize
import views.stylesheets.MainStylesheet.Companion.defaultSpacing

class SettingsPopOver : View() {
  private val controller: SettingsController by inject()
  private val authUser: AuthUserModel by inject()

  override val root = vbox {
    prefWidth = 300.0
    spacing = defaultSpacing

    vbox {
      paddingAll = defaultSpacing
      spacing = defaultSpacing

      run {
        imageview("user.png") {
          avatarSize()
          alignment = Pos.CENTER
        }
      }
      button("Set profile picture").action { controller.setProfilePicture() }
    }

    vbox {
      form {
        fieldset("Name") {
          textfield(authUser.firstName).required()
        }
        fieldset("Username") {
          textfield(authUser.username).required()
        }
        button("SAVE") {
          useMaxWidth = true

          enableWhen {
            authUser.firstName.isNotEmpty
              .and(authUser.username.isNotEmpty)
              .and(
                authUser.dirtyStateFor(AuthUserModel::firstName)
                  .or(authUser.dirtyStateFor(AuthUserModel::username))
              )
          }
          action { controller.save(authUser) }
        }
      }
    }
  }
}