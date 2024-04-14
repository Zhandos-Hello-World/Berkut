package kz.cicada.berkut.feature.children.presentation.childs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.children.data.model.ChildrenResponse
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.progress.CustomProgressBar

@Composable
fun ChildrenContent(
    controller: ChildrenController,
    uiState: ChildrenUIState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundPrimary)
    ) {
        Column(
            modifier = Modifier
                .weight(1F)
                .background(MaterialTheme.additionalColors.backgroundPrimary)
                .padding(vertical = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            when (uiState) {
                is ChildrenUIState.Data -> {
                    uiState.list.forEach {
                        Item(
                            name = it.username,
                            userId = it.userID,
                            controller = controller,
                        )
                        Divider()
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                is ChildrenUIState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize().weight(1F),
                        contentAlignment = Alignment.Center,
                    ) {
                        CustomProgressBar(
                            modifier = Modifier.size(44.dp),
                            color = MaterialTheme.additionalColors.coreBlack
                        )
                    }
                }
            }
        }
        CommonPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp, vertical = 16.dp
                ),
            text = "Add child",
            onClick = controller::onAddChildClick,
        )
    }

}

@Composable
fun Item(
    name: String,
    userId: Int,
    controller: ChildrenController,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                controller.onChildrenClick(userId)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview
@Composable
fun SaveLocationListContentLoadingPreview() {
    AppTheme {
        ChildrenContent(
            controller = object : ChildrenController {
                override fun navigateUp() = Unit
                override fun onChildrenClick(id: Int) = Unit
                override fun onAddChildClick() = Unit
            }, uiState = ChildrenUIState.Loading
        )
    }
}

@Preview
@Composable
fun SaveLocationListContentDataPreview() {
    AppTheme {
        ChildrenContent(
            controller = object : ChildrenController {
                override fun navigateUp() = Unit
                override fun onChildrenClick(id: Int) = Unit
                override fun onAddChildClick() = Unit
            }, uiState = ChildrenUIState.Data(
                list = listOf(
                    ChildrenResponse(
                        username = "Zhandos1",
                        role = "CHILD",
                        userID = 12,
                    ),
                    ChildrenResponse(
                        username = "Zhandos2",
                        role = "CHILD",
                        userID = 13,
                    ),
                    ChildrenResponse(
                        username = "Zhandos3",
                        role = "CHILD",
                        userID = 14,
                    ),
                    ChildrenResponse(
                        username = "Zhandos4",
                        role = "CHILD",
                        userID = 15,
                    ),
                )
            )
        )
    }
}