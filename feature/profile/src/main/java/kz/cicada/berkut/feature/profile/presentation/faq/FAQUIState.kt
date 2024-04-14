package kz.cicada.berkut.feature.profile.presentation.faq

import kz.cicada.berkut.feature.profile.presentation.model.FAQDvo

sealed interface FAQUIState {
    data class Data(
        val list: List<FAQDvo>
    ) : FAQUIState

    data object Loading : FAQUIState
}