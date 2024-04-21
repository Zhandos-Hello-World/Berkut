package kz.cicada.berkut.feature.tasks.presentation.list

import kz.cicada.berkut.lib.core.ui.compose.widgets.expandable.SelectedListItem

data class TaskListDataState(
    val list: MutableList<SelectedListItem>,
    val isParent: Boolean,
)