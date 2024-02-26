package kz.cicada.berkut.feature.chooser.presentation.feature.simple

import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import android.os.Parcelable
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.SystemEvent

interface SimpleChooserBehavior : Parcelable {

    fun getHeaderOrNull(): VmRes<CharSequence>? = null

    fun isCloseButtonVisible(): Boolean = true

    suspend fun getItemList(): List<ChooserDvo>

    fun getEmptyStateDetails(): ChooserDvo.EmptyState = ChooserDvo.EmptyState

    suspend fun onSelectableTextClick(item: ChooserDvo.SelectableText): List<SystemEvent> =
        emptyList()

    suspend fun onSecondaryButtonClick(item: ChooserDvo.SecondaryButton): List<SystemEvent> =
        emptyList()
}
