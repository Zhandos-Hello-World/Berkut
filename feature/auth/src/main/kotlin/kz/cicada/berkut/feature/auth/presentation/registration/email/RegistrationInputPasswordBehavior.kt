package kz.cicada.berkut.feature.auth.presentation.registration.email

import androidx.compose.ui.text.input.ImeAction
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.feature.auth.presentation.input.password.InputPasswordBehavior
import kz.cicada.berkut.feature.auth.presentation.input.password.PasswordFieldData
import kz.cicada.berkut.lib.core.empty
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.SystemEvent

@Parcelize
internal class RegistrationInputPasswordBehavior : InputPasswordBehavior {

    override suspend fun onPrimaryButtonClick(value: String): List<SystemEvent> {
        return emptyList()
    }

    override fun getInputItems(): List<PasswordFieldData> {
        return listOf(
            PasswordFieldData(
                value = String.empty,
                label = VmRes.StrRes(R.string.password),
                imeAction = ImeAction.Done,
            ),
        )
    }

    override fun getPrimaryButtonText(): VmRes<CharSequence> {
        return VmRes.StrRes(R.string.continue_text)
    }

    override fun validateFields(fieldsData: List<PasswordFieldData>): List<PasswordFieldData>? {
        return null
    }
}