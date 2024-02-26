package kz.cicada.berkut.feature.chooser.presentation.feature.searchable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchableChooserLauncher(
    val behavior: SearchableChooserBehavior,
) : Parcelable
