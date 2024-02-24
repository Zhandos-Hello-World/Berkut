package am.strongte.hub.auth.presentation.code

import androidx.compose.runtime.Stable
import kz.cicada.berkut.lib.core.localization.string.VmRes

@Stable
internal data class InputCodeUiState(
    val otpValue: String,
    val secondsLeft: Int = 60,
    val canSendAgain: Boolean = false,
    val isConfirmButtonLoading: Boolean = false,
    val codeError: VmRes<CharSequence>? = null,
)
