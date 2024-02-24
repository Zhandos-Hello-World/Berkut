package kz.cicada.berkut.lib.core.ui.compose.widgets.button.common

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ButtonDefaults.zeroElevation(): ButtonElevation {
    return elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 0.dp,
    )
}