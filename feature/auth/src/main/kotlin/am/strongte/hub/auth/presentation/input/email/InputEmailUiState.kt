package am.strongte.hub.auth.presentation.input.email

import androidx.compose.runtime.Stable
import kz.cicada.berkut.lib.core.localization.string.VmRes

@Stable
internal data class InputEmailUiState(
    val isPrimaryButtonLoading: Boolean = false,
    val textError: VmRes<CharSequence>? = null,
    val isPrimaryButtonEnabled: Boolean = true,
    val title: VmRes<CharSequence>,
    val description: VmRes<CharSequence>,
    val primaryButtonText: VmRes<CharSequence>,
    val phoneNumber: String,
    val userName: String,
    val phoneNumberError: VmRes<CharSequence>? = null,
)