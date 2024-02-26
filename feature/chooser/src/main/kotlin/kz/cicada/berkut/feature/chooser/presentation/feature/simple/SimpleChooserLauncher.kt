package kz.cicada.berkut.feature.chooser.presentation.feature.simple

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimpleChooserLauncher(
    val behavior: SimpleChooserBehavior,
) : Parcelable