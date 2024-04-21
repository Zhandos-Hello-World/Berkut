package kz.cicada.berkut.feature.children.presentation.childs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChildrenLauncher(
    val behavior: ChildrenBehavior,
) : Parcelable