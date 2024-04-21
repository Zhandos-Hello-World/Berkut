package kz.cicada.berkut.feature.tasks.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.base.LoadingType
import kz.cicada.berkut.lib.core.ui.base.ViewState
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.expandable.SelectedListItem
import kz.cicada.berkut.lib.core.ui.compose.widgets.expandable.SelectorListColumn
import kz.cicada.berkut.lib.core.ui.compose.widgets.progress.CustomProgressBar
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun TaskListContent(
    controller: TaskListController,
    uiState: ViewState<TaskListDataState>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundDark),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Toolbar(
            title = "Task List",
            navigateUp = controller::navigateUp,
        )

        when (uiState) {
            is ViewState.Loading -> {
                Spacer(modifier = Modifier.weight(1F))
                CustomProgressBar(
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.CenterHorizontally),
                    color = MaterialTheme.additionalColors.backgroundAccent,
                )
                Spacer(modifier = Modifier.weight(1F))
            }
            is ViewState.Data -> {
                SelectorListColumn(
                    modifier = Modifier.weight(1F).padding(16.dp),
                    items = uiState.data.list,
                    onItemClick = controller::onTaskClick,
                    onDeleteClick = { controller.onDeleteTask(it) },
                    onMoveClick = { controller.onMoveClick(it) }
                )
            }
            else -> Unit
        }

        if (uiState is ViewState.Data && uiState.data.isParent) {
            CommonPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp),
                text = "Add Task",
                onClick = controller::onAddTaskClick,
            )
        }
    }
}

@Preview
@Composable
fun TaskListLoadingContentPreview() {
    AppTheme {
        TaskListContent(
            controller = object : TaskListController {
                override fun navigateUp() = Unit
                override fun onDeleteTask(model: SelectedListItem.SelectedItem) = Unit
                override fun onTaskClick(model: SelectedListItem.SelectedItem) = Unit
                override fun onMoveClick(model: SelectedListItem.SelectedItem) = Unit
                override fun onAddTaskClick() = Unit
            },
            uiState = ViewState.Loading(LoadingType.Progress),
        )
    }
}

@Preview
@Composable
fun TaskListDataContentPreview() {
    AppTheme {
        TaskListContent(
            controller = object : TaskListController {
                override fun navigateUp() = Unit
                override fun onDeleteTask(model: SelectedListItem.SelectedItem) = Unit
                override fun onTaskClick(model: SelectedListItem.SelectedItem) = Unit
                override fun onMoveClick(model: SelectedListItem.SelectedItem) = Unit
                override fun onAddTaskClick() = Unit
            },
            uiState = ViewState.Data(
                TaskListDataState(
                    mutableListOf(
                        SelectedListItem(
                            selector = "First selector",
                            movable = true,
                            items = mutableListOf(
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "First selector first item",
                                    coins = 5,
                                ),
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "First selector second item",
                                    coins = 5,
                                )
                            ),
                        ),
                        SelectedListItem(
                            selector = "Second selector",
                            movable = true,
                            items = mutableListOf(
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "Second selector first item",
                                    coins = 5,
                                )
                            )
                        ),

                        SelectedListItem(
                            selector = "Second selector",
                            movable = true,
                            items = mutableListOf(
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "Second selector first item",
                                    coins = 5,
                                )
                            )
                        ),

                        SelectedListItem(
                            selector = "Second selector",
                            movable = true,
                            items = mutableListOf(
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "Second selector first item",
                                    coins = 5,
                                )
                            )
                        ),

                        SelectedListItem(
                            selector = "Second selector",
                            movable = true,
                            items = mutableListOf(
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "Second selector first item",
                                    coins = 5,
                                )
                            )
                        ),

                        SelectedListItem(
                            selector = "Second selector",
                            movable = true,
                            items = mutableListOf(
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "Second selector first item",
                                    coins = 5,
                                ),
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "Second selector first item",
                                    coins = 5,
                                ),
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "Second selector first item",
                                    coins = 5,
                                ),
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "Second selector first item",
                                    coins = 5,
                                )
                            )
                        ),
                        SelectedListItem(
                            selector = "Third selector",
                            movable = true,
                            items = mutableListOf(
                                SelectedListItem.SelectedItem(
                                    id = 1,
                                    name = "Third selector first item",
                                    coins = 5,
                                )
                            )
                        )
                    ),
                    isParent = true,
                ),
            ),
        )
    }
}