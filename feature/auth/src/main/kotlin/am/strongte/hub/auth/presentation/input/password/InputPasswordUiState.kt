package am.strongte.hub.auth.presentation.input.password

import androidx.compose.runtime.Stable
import kz.cicada.berkut.lib.core.localization.string.VmRes

@Stable
internal data class InputPasswordUiState(
    val isPrimaryButtonLoading: Boolean = false,
    val isPrimaryButtonEnabled: Boolean = true,
    val userName: String,
    val primaryButtonText: VmRes<CharSequence>,
    val inputItems: List<PasswordFieldData>,
    val warnings: List<VmRes<CharSequence>>,
)