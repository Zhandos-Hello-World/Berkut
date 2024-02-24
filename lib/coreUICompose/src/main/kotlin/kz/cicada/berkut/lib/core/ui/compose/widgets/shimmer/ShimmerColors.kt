package kz.cicada.berkut.lib.core.ui.compose.widgets.shimmer

import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun getShimmerColors() = listOf(
    MaterialTheme.additionalColors.backgroundLight,
    MaterialTheme.additionalColors.elementsLight,
    MaterialTheme.additionalColors.backgroundLight,
)
