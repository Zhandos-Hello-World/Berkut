package kz.cicada.berkut.feature.children.presentation.childs

import android.os.Parcelable
import kz.cicada.berkut.lib.core.ui.event.ActionEvent
import org.koin.core.component.KoinComponent

interface ChildrenBehavior : Parcelable, KoinComponent {
    fun onClickNavigate(childId: Int): List<ActionEvent>
}
