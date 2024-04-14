package kz.cicada.berkut.feature.sos.presentation.hotline

import androidx.compose.runtime.Stable

@Stable
sealed interface AddHotlineNumberState {

    data class Data(
        val name: String,
        val phoneNumber: String,
    ) : AddHotlineNumberState
}