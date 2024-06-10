package kz.cicada.berkut.feature.children.presentation.operation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kz.cicada.berkut.feature.children.presentation.operation.ChildrenOperationController
import kz.cicada.berkut.feature.children.presentation.operation.ChildrenOperationUIState
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun ChildrenOperationContent(
    uiState: ChildrenOperationUIState,
    controller: ChildrenOperationController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Toolbar(
            navigateUp = controller::onNavigateBack,
            title = (uiState as? ChildrenOperationUIState.Data)?.name ?: "Children Operation",
        )
        when (uiState) {
            is ChildrenOperationUIState.Loading -> ChildrenOperationLoadingContent()
            is ChildrenOperationUIState.Data -> ChildrenOperationDataContent(
                uiState = uiState,
                controller = controller,
            )
        }
    }
}

@Preview
@Composable
private fun ChildrenOperationLoadingMainContentPreview() {
    AppTheme {
        ChildrenOperationContent(
            uiState = ChildrenOperationUIState.Loading,
            controller = object : ChildrenOperationController {
                override fun onNavigateBack() = Unit
                override fun onTaskListClick() = Unit
                override fun onAddHotlineClick() = Unit
            },
        )
    }
}

@Preview
@Composable
private fun ChildrenOperationDataMainContentPreview() {
    AppTheme {
        ChildrenOperationContent(
            uiState = ChildrenOperationUIState.Data(
                name = "Name",
                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                phoneNumber = "+7 777 777 77 77",
                coins = "100",
            ),
            controller = object : ChildrenOperationController {
                override fun onNavigateBack() = Unit
                override fun onTaskListClick() = Unit
                override fun onAddHotlineClick() = Unit
            },
        )
    }
}