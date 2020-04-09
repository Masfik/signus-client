package misc

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.MultipleSelectionModel

class ChatSelectionModel<T> : MultipleSelectionModel<T>() {
  override fun clearSelection(p0: Int) {}

  override fun clearSelection() {}

  override fun selectLast() {}

  override fun isSelected(p0: Int): Boolean = false

  override fun getSelectedIndices(): ObservableList<Int> = FXCollections.emptyObservableList()

  override fun selectAll() {}

  override fun getSelectedItems(): ObservableList<T> = FXCollections.emptyObservableList()

  override fun select(p0: Int) {}

  override fun select(p0: T) {}

  override fun isEmpty(): Boolean = true

  override fun selectNext() {}

  override fun selectPrevious() {}

  override fun selectIndices(p0: Int, vararg p1: Int) {}

  override fun selectFirst() {}

  override fun clearAndSelect(p0: Int) {}
}