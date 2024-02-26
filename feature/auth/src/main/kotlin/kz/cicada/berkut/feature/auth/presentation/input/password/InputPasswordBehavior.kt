package kz.cicada.berkut.feature.auth.presentation.input.password

import android.os.Parcelable
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.SystemEvent
import org.koin.core.component.KoinComponent

internal interface InputPasswordBehavior : Parcelable, KoinComponent {

    suspend fun onPrimaryButtonClick(value: String): List<SystemEvent>

    fun getInputItems(): List<PasswordFieldData>

    fun getPrimaryButtonText(): VmRes<CharSequence>

    fun validateFields(fieldsData: List<PasswordFieldData>): List<PasswordFieldData>?
}
