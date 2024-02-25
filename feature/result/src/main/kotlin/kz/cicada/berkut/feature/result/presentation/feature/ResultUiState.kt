package kz.cicada.berkut.feature.result.presentation.feature

import androidx.compose.runtime.Stable
import kz.cicada.berkut.lib.core.localization.string.VmRes

@Stable
data class ResultUiState(
    val title: VmRes<CharSequence>,
    val body: VmRes<CharSequence>,
    val primaryButtonText: VmRes<CharSequence>,
)
