package kz.cicada.berkut.feature.profile.presentation.hotline.listHotline

import androidx.compose.runtime.Stable

@Stable
sealed interface HotlineListState {
    object Loading : HotlineListState

    data class Data(
        val list: List<HotlineItem>,
    ) : HotlineListState
}

data class HotlineItem(
    val name: String,
    val phoneNumber: String,
)