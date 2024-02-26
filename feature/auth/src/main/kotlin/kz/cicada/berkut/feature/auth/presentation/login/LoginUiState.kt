package kz.cicada.berkut.feature.auth.presentation.login

import androidx.compose.runtime.Stable
import kz.cicada.berkut.lib.core.localization.string.VmRes

@Stable
internal data class LoginUiState(
    val email: String,
    val password: String,
    val isPasswordVisible: Boolean = false,
    val focusedField: FocusedField = FocusedField.Nothing,
    val isEnterLoading: Boolean = false,
    val emailError: VmRes<CharSequence>? = null,
    val passwordError: VmRes<CharSequence>? = null,
    val isForgotEnabled: Boolean = false,
)