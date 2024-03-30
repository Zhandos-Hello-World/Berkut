package kz.cicada.berkut.feature.profile.presentation.home

import androidx.compose.runtime.Stable

@Stable
sealed interface HomeUIState {
    data object ParentData : HomeUIState
    data object ChildData : HomeUIState
    data object NotInit : HomeUIState
}