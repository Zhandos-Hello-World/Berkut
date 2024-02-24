package am.strongte.hub.auth.presentation.input.email

import android.os.Parcelable
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.SystemEvent
import org.koin.core.component.KoinComponent

internal interface InputEmailBehavior : Parcelable, KoinComponent {

    suspend fun onPrimaryButtonClick(value: String): List<SystemEvent>

    fun getTitle(): VmRes<CharSequence>

    fun getDescription(): VmRes<CharSequence>

    fun getPrimaryButtonText(): VmRes<CharSequence>
}
