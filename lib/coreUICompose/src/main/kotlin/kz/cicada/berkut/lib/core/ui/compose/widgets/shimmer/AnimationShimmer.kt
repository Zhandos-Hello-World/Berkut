package kz.cicada.berkut.lib.core.ui.compose.widgets.shimmer

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

@Composable
fun ShimmerAnimation(shimmerBody: @Composable (brush: Brush) -> Unit) {
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = 0F,
        targetValue = 1000F,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 900,
                easing = FastOutSlowInEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "",
    )

    val brush = Brush.linearGradient(
        colors = getShimmerColors(),
        start = Offset(10F, 10F),
        end = Offset(translateAnim, translateAnim),
    )
    shimmerBody(brush)
}