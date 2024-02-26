package kz.cicada.berkut.feature.chooser.presentation.feature.searchable

import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import android.os.Parcelable
import kz.cicada.berkut.lib.core.localization.string.VmRes
import org.koin.core.component.KoinComponent

interface SearchableChooserBehavior : Parcelable, KoinComponent {

    fun getSearchHint(): VmRes<CharSequence>

    fun getInitialSearchQuery(): String

    suspend fun getItemList(): List<ChooserDvo>

    fun getEmptyStateDetails(): ChooserDvo.EmptyState {
        return ChooserDvo.EmptyState
    }

    fun getResultKey(): String? = null

    suspend fun onSearchQueryChange(
        searchQuery: String,
    ): List<ChooserDvo>
}
