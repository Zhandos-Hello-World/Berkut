package kz.cicada.berkut.feature.tasks.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.base.LoadingType
import kz.cicada.berkut.lib.core.ui.base.ViewState
import kz.cicada.berkut.lib.core.ui.compose.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun TaskDetailsContent(
    controller: TaskDetailsController,
    uiState: ViewState<TaskDetailsDataState>,
) {
    when (uiState) {
        is ViewState.Data -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .background(MaterialTheme.additionalColors.backgroundPrimary),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Toolbar(
                    navigateUp = controller::onNavigateBack, title = "Task details"
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Task name: ${uiState.data.taskName}"
                    )
                    Text(
                        text = "Description: ${uiState.data.taskDescription}"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = uiState.data.coins.toString(),
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.additionalColors.elementsLowContrast,
                        )

                        Image(
                            modifier = Modifier
                                .size(44.dp)
                                .padding(horizontal = 8.dp),
                            painter = painterResource(R.drawable.ic_coins),
                            contentDescription = null,
                        )
                    }
                }
            }
        }

        else -> Unit
    }
}

@Preview
@Composable
fun TaskDetailsLoadingContentPreview() {
    AppTheme {
        TaskDetailsContent(
            controller = object : TaskDetailsController {
                override fun onNavigateBack() = Unit
                override fun onDoneClick() = Unit
            },
            uiState = ViewState.Loading(LoadingType.Progress),
        )
    }
}

@Preview
@Composable
fun TaskDetailsDataContentPreview() {
    AppTheme {
        TaskDetailsContent(
            controller = object : TaskDetailsController {
                override fun onNavigateBack() = Unit
                override fun onDoneClick() = Unit
            },
            uiState = ViewState.Data<TaskDetailsDataState>(
                TaskDetailsDataState(
                    taskName = " EWKFOPKE WOPKFOPEW KOFPKEW OPKFOPEW",
                    taskDescription = "GPWEFOKWEOK EWPO KEW",
                    coins = 32,
                )
            ),
        )
    }
}