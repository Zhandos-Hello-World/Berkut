package kz.cicada.berkut.feature.shareqr.presentation.share

sealed interface ShareQRUIState {

    data object Loading : ShareQRUIState

    data class Data(
        val url: String,
    ) : ShareQRUIState
}