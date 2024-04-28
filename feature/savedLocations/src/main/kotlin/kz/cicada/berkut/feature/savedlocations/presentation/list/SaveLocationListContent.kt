package kz.cicada.berkut.feature.savedlocations.presentation.list

import androidx.compose.foundation.background
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
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.savedlocations.data.model.SavedLocationResponse
import kz.cicada.berkut.lib.core.ui.compose.R
import kz.cicada.berkut.lib.core.ui.compose.menu.MenuLazyItemDivider
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.progress.CustomProgressBar
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun SaveLocationListContent(
    controller: SaveLocationListController,
    uiState: SaveLocationListUIState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundPrimary)
    ) {
        Toolbar(
            navigateUp = controller::navigateUp,
        )

        Column(
            modifier = Modifier
                .weight(1F)
                .background(MaterialTheme.additionalColors.backgroundPrimary)
                .verticalScroll(rememberScrollState())
        ) {

            when (uiState) {
                is SaveLocationListUIState.Data -> {
                    SaveLocationDataListContent(
                        items = uiState.list,
                        onDeleteClick = controller::onDeleteClick,
                    )
                }

                is SaveLocationListUIState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1F),
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
        if (uiState is SaveLocationListUIState.Data && uiState.isParent) {
            CommonPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp, vertical = 16.dp
                    ),
                text = "Create new saved location",
                onClick = controller::onAddSaveLocationClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SaveLocationDataListContent(
    items: MutableList<SavedLocationResponse>,
    onDeleteClick: (SavedLocationResponse) -> Unit,
) {
    items.forEach { value ->
        val state = rememberDismissState(
            confirmStateChange = {
                if (it == DismissValue.DismissedToStart) {
                    items.remove(value)
                    onDeleteClick(value)
                }
                true
            },
        )
        SwipeToDismiss(
            state = state,
            background = {
                val color = when (state.dismissDirection) {
                    DismissDirection.EndToStart -> {
                        items.remove(value)
                        Color.Red
                    }
                    else -> Color.Transparent
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = when (state.dismissDirection) {
                            DismissDirection.EndToStart -> Icons.Default.Delete
                            else -> Icons.Default.Check
                        },
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            },
            dismissContent = {
                Item(
                    name = value.name,
                    latitude = value.latitude,
                    longitude = value.longitude,
                )
                Divider()
            },
            directions = setOf(
                DismissDirection.EndToStart,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun Item(
    name: String,
    latitude: Double,
    longitude: Double,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "lat: $latitude, lon: $longitude",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.additionalColors.backgroundAccent
            )

        }

        Spacer(modifier = Modifier.weight(1F))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_notifications),
            contentDescription = null,
            tint = Color.Red,
        )
    }
}

@Preview
@Composable
fun SaveLocationListContentLoadingPreview() {
    AppTheme {
        SaveLocationListContent(
            controller = object : SaveLocationListController {
                override fun navigateUp() = Unit
                override fun onAddSaveLocationClick() = Unit
                override fun onDeleteClick(savedLocation: SavedLocationResponse) = Unit
            },
            uiState = SaveLocationListUIState.Loading,
        )
    }
}

@Preview
@Composable
fun SaveLocationListContentDataPreview() {
    AppTheme {
        SaveLocationListContent(
            controller = object : SaveLocationListController {
                override fun navigateUp() = Unit
                override fun onAddSaveLocationClick() = Unit
                override fun onDeleteClick(savedLocation: SavedLocationResponse) = Unit
            },
            uiState = SaveLocationListUIState.Data(
                list = mutableListOf(
                    SavedLocationResponse(
                        id = 1,
                        latitude = 32.0,
                        longitude = 32.0,
                        name = "Zhandos",
                        radius = 23.43,
                        notify = true,
                    ),
                    SavedLocationResponse(
                        id = 2,
                        latitude = 32.0,
                        longitude = 32.0,
                        name = "Zhandos2",
                        radius = 23.43,
                        notify = true,
                    ),
                    SavedLocationResponse(
                        id = 3,
                        latitude = 32.0,
                        longitude = 32.0,
                        name = "Zhandos3",
                        radius = 23.43,
                        notify = true,
                    ),
                ),
                isParent = true,
            ),
        )
    }
}