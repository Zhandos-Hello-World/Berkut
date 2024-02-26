package kz.cicada.berkut.feature.chooser.presentation.feature.simple.ui

import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.localization.string.resolve
import kz.cicada.berkut.lib.core.ui.base.ViewState
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.TopHandle
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonSecondaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.list.TextItem
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.BottomSheetToolbar

@Composable
fun SimpleChooserContent(
    uiState: ViewState<SimpleChooserUiState>,
    controller: SimpleChooserController,
) {
    Column(modifier = Modifier) {
        TopHandle(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 12.dp, bottom = 8.dp),
        )

        when (uiState) {
            is ViewState.Data -> {
                when (val state = uiState.data) {
                    is SimpleChooserUiState.Data -> SimpleChooserDataContent(
                        uiState = state,
                        controller = controller,
                    )

                    is SimpleChooserUiState.Loading -> SimpleChooserLoadingContent(
                        controller = controller,
                    )

                    is SimpleChooserUiState.Error -> SimpleChooserErrorContent(
                        errorState = state,
                        controller = controller,
                    )
                }
            }

            else -> Unit
        }

        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
private fun SimpleChooserDataContent(
    uiState: SimpleChooserUiState.Data,
    controller: SimpleChooserController,
) {
    BottomSheetToolbar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp),
        title = uiState.chooserHeader,
        isBackButtonVisible = uiState.isBackButtonVisible,
        onBackClick = controller::onCloseButtonClick,
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        itemsIndexed(uiState.chooserItems) { _, item ->
            when (item) {
                is ChooserDvo.SelectableText -> {
                    TextItem(
                        text = item.text.resolve(),
                        selected = item.isSelected,
                        onClick = { controller.onSelectableClick(item) },
                    )
                    if (item.dividerVisible) {
                        Divider(
                            color = MaterialTheme.additionalColors.backgroundDark,
                        )
                    }
                }

                is ChooserDvo.SecondaryButton -> CommonSecondaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    text = item.text.resolve(),
                    onClick = { controller.onSecondaryButtonClick(item) },
                    leadingIcon = item.leadingIcon?.let { ImageVector.vectorResource(id = it) },
                )

                else -> Unit
            }
        }
    }
}