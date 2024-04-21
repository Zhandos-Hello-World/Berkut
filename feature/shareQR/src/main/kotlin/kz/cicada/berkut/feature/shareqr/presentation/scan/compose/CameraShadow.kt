package kz.cicada.berkut.feature.shareqr.presentation.scan.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme

@Composable
fun TransparentClipLayout(
    modifier: Modifier,
) {
    val offsetInPx: Float
    val localDensity = LocalDensity.current

    var offsetY by remember { mutableStateOf(0.dp) }

    var columnWidthPx by remember { mutableFloatStateOf(0f) }
    var columnWidthDp by remember { mutableStateOf(0.dp) }

    with(localDensity) {
        offsetInPx = offsetY.toPx()
    }

    Canvas(
        modifier = modifier.onGloballyPositioned { coordinates ->
            columnWidthPx = coordinates.size.width.toFloat() / 1.2F
            columnWidthDp = with(localDensity) { coordinates.size.width.toDp() }
            offsetY = columnWidthDp / 3
        },
    ) {
        val canvasWidth = size.width

        with(drawContext.canvas.nativeCanvas) {
            val checkPoint = saveLayer(null, null)
            drawRect(Color(0x77000000))
            drawRoundRect(
                topLeft = Offset(
                    x = (canvasWidth - columnWidthPx) / 2, y = offsetInPx
                ),
                size = Size(columnWidthPx, columnWidthPx),
                cornerRadius = CornerRadius(30f, 30f),
                color = Color.Transparent,
                blendMode = BlendMode.Clear,
            )

            drawRoundRect(
                topLeft = Offset(
                    x = (canvasWidth - columnWidthPx) / 2, y = offsetInPx
                ),
                size = Size(columnWidthPx, columnWidthPx),
                cornerRadius = CornerRadius(30f, 30f),
                color = Color.Red,
                blendMode = BlendMode.Color,
                style = Stroke(
                    width = 3.dp.toPx(),
                ),
            )
            restoreToCount(checkPoint)
        }
    }
}

@Preview
@Composable
private fun CanvasShapeSamplePreview() {
    AppTheme {
        TransparentClipLayout(
            modifier = Modifier.fillMaxSize(),
        )
    }
}