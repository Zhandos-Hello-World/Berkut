package kz.cicada.berkut.feature.language.presentation.onboarding

import androidx.annotation.StringRes

internal sealed interface OnBoardingLanguageUiState {
    data class Data(
        @StringRes val resId: Int,
    ) : OnBoardingLanguageUiState
}
