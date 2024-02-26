package kz.cicada.berkut.feature.chooser.presentation.feature.searchable.ui

import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.localization.string.resolve
import kz.cicada.berkut.lib.core.ui.base.ViewState
import kz.cicada.berkut.lib.core.ui.compose.widgets.TopHandle
import kz.cicada.berkut.lib.core.ui.compose.widgets.input.CommonInputField
import kz.cicada.berkut.lib.core.ui.compose.widgets.list.TextItem

@Composable
fun SearchableChooserContent(
    uiState: ViewState<SearchableChooserUiState>,
    controller: SearchableChooserController,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopHandle(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp),
        )

        when (uiState) {
            is ViewState.Data -> {
                when (val state = uiState.data) {
                    is SearchableChooserUiState.Data -> SearchableChooserBody(
                        uiState = state,
                        controller = controller,
                    )

                    else -> Unit
                }
            }

            else -> Unit
        }
    }

}

@Composable
fun SearchableChooserBody(
    uiState: SearchableChooserUiState.Data,
    controller: SearchableChooserController,
) {
    CommonInputField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
        text = uiState.searchQuery,
        onValueChange = controller::searchTextChanged,
        labelText = uiState.searchHint.resolve(),
        placeholderText = uiState.searchHint.resolve(),
    )

    LazyColumn {
        items(uiState.chooserItems) { item ->
            when (item) {
                is ChooserDvo.SelectableText -> TextItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = item.text.resolve(),
                    selected = item.isSelected,
                    onClick = { controller.onSelectableClick(item) },
                )

                else -> Unit
            }
        }
    }
}
