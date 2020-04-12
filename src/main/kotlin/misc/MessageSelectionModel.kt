package misc

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.MultipleSelectionModel
import models.Message

/**
 * Selection model for messages: the default model for ListViews is not suitable for a chatting application.
 * This class aims to change how messages can be selected and offer extra functionalities in the future (e.g. copying).
 * For the time being, it disables selection altogether. WORK IN PROGRESS.
 */
class MessageSelectionModel : MultipleSelectionModel<Message>() {
  override fun clearSelection(p0: Int) {}

  override fun clearSelection() {}

  override fun selectLast() {}

  override fun isSelected(p0: Int): Boolean = false

  override fun getSelectedIndices(): ObservableList<Int> = FXCollections.emptyObservableList()

  override fun selectAll() {}

  override fun getSelectedItems(): ObservableList<Message> = FXCollections.emptyObservableList()

  override fun select(p0: Int) {}

  override fun select(p0: Message) {}

  override fun isEmpty(): Boolean = true

  override fun selectNext() {}

  override fun selectPrevious() {}

  override fun selectIndices(p0: Int, vararg p1: Int) {}

  override fun selectFirst() {}

  override fun clearAndSelect(p0: Int) {}
}