package am.strongte.hub.auth.presentation.input.password

import androidx.compose.ui.text.input.ImeAction
import kz.cicada.berkut.lib.core.localization.string.VmRes

internal data class PasswordFieldData(
    val value: String,
    val label: VmRes<CharSequence>,
    val isPasswordVisible: Boolean = false,
    val textError: VmRes<CharSequence>? = null,
    val imeAction: ImeAction = ImeAction.Done,
)
