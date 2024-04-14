package kz.cicada.berkut.feature.sos.presentation.hotline.listHotline

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import kz.cicada.berkut.feature.sos.R

@Stable
sealed interface HotlineListState {
    object Loading : HotlineListState

    data class Data(
        val list: List<HotlineItem>,
    ) : HotlineListState
}

data class HotlineItem(
    @DrawableRes val iconRes: Int = R.drawable.ic_supervisor_account,
    val name: String,
    val phoneNumber: String,
)