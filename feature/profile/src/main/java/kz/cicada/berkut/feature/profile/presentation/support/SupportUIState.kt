package kz.cicada.berkut.feature.profile.presentation.support

import androidx.compose.runtime.Stable

@Stable
sealed interface SupportUIState {

    data object NotInit: SupportUIState
    data class Data(
        val id: String,
    ) : SupportUIState
}