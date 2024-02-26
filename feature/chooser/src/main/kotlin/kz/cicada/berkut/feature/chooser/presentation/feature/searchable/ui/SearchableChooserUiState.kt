package kz.cicada.berkut.feature.chooser.presentation.feature.searchable.ui

import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import kz.cicada.berkut.lib.core.localization.string.VmRes

@Stable
sealed interface SearchableChooserUiState {
    data class Error(
        @DrawableRes val errorImage: Int,
        val errorDescription: String,
    ) : SearchableChooserUiState

    object Loading : SearchableChooserUiState

    data class Data(
        val searchQuery: String,
        val searchHint: VmRes<CharSequence>,
        val chooserItems: List<ChooserDvo>,
        val emptyStateDetails: ChooserDvo.EmptyState,
    ) : SearchableChooserUiState
}
