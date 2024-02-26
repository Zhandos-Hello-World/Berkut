package kz.cicada.berkut.feature.auth.presentation.name

import androidx.compose.runtime.Stable
import kz.cicada.berkut.lib.core.localization.string.VmRes

@Stable
internal data class InputNameUiState(
    val isPrimaryButtonLoading: Boolean = false,
    val isPrimaryButtonEnabled: Boolean = true,
    val username: String,
    val primaryButtonText: VmRes<CharSequence>,
    val warnings: List<VmRes<CharSequence>>,
)