package kz.cicada.berkut.feature.chooser.presentation.feature.simple.ui

import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import kz.cicada.berkut.lib.core.localization.string.VmRes

@Stable
sealed interface SimpleChooserUiState {

    data class Error(
        @DrawableRes val errorImage: Int,
        val errorDescription: VmRes<CharSequence>,
    ) : SimpleChooserUiState

    object Loading : SimpleChooserUiState

    data class Data(
        val chooserHeader: VmRes<CharSequence>?,
        val chooserItems: List<ChooserDvo>,
        val emptyStateDetails: ChooserDvo.EmptyState,
        val isBackButtonVisible: Boolean,
    ) : SimpleChooserUiState
}
