package kz.cicada.berkut.feature.tasks.presentation.list

import kz.cicada.berkut.lib.core.ui.compose.widgets.expandable.SelectedListItem

interface TaskListController {
    fun navigateUp()
    fun onDeleteTask(model: SelectedListItem.SelectedItem)
    fun onTaskClick(model: SelectedListItem.SelectedItem)
    fun onMoveClick(model: SelectedListItem.SelectedItem)
    fun onAddTaskClick()
}