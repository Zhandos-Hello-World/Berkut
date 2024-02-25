package kz.cicada.berkut.feature.result.presentation.feature

import android.os.Parcelable
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.SystemEvent
import org.koin.core.component.KoinComponent

interface ResultBehavior : Parcelable, KoinComponent {

    fun getTitle(): VmRes<CharSequence>

    fun getBody(): VmRes<CharSequence>

    fun getPrimaryButtonText(): VmRes<CharSequence>

    suspend fun onPrimaryButtonClick(): List<SystemEvent>

    suspend fun onNavigateBack(): List<SystemEvent>

}