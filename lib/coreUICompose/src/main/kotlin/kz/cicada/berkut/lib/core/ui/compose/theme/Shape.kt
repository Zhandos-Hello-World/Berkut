package kz.cicada.berkut.lib.core.ui.compose.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
)

val Shapes.BottomSheetShape
    @Composable get() = RoundedCornerShape(16.dp, 16.dp)

val Shapes.cardShape
    @Composable get() = RoundedCornerShape(12.dp)
