package kz.cicada.berkut.lib.core.ui.compose.widgets.button.common

import kz.cicada.berkut.lib.core.ui.compose.widgets.progress.CustomProgressBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DefaultProgressButtonContent(
    modifier: Modifier = Modifier,
    text: String?,
    style: TextStyle,
    color: Color,
    loading: Boolean,
    icon: ImageVector? = null,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (loading) {
            CustomProgressBar(color = color)
        } else {
            Row {
                icon?.let {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = icon,
                        contentDescription = null,
                        tint = color,
                    )
                }
                if (icon != null && text != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                text?.let {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        text = text,
                        textAlign = TextAlign.Center,
                        style = style,
                        color = color,
                    )
                }
            }
        }
    }
}