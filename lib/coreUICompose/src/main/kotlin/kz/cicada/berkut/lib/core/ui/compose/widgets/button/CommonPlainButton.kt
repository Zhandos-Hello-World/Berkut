package kz.cicada.berkut.lib.core.ui.compose.widgets.button

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.common.DefaultProgressButtonContent
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.common.zeroElevation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CommonPlainButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    onClick: () -> Unit,
    style: TextStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
    colors: ButtonColors? = null,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    content: @Composable RowScope.() -> Unit = {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        DefaultProgressButtonContent(
            text = text,
            style = style,
            color = when {
                !enabled -> MaterialTheme.additionalColors.backgroundAccent.copy(alpha = 0.8f)
                isPressed -> MaterialTheme.additionalColors.shadesAccentDark2
                else -> MaterialTheme.additionalColors.backgroundAccent
            },
            loading = loading,
            modifier = Modifier.height(24.dp),
        )
    },
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        elevation = ButtonDefaults.zeroElevation(),
        colors = colors ?: ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            disabledBackgroundColor = Color.Transparent,
        ),
        content = content,
        shape = shape,
        contentPadding = contentPadding,
    )
}

@Preview
@Composable
fun CommonPlainButtonPreview() {
    AppTheme {
        var loading by remember { mutableStateOf(true) }
        CommonPlainButton(
            text = "Preview",
            modifier = Modifier.width(120.dp),
            enabled = true,
            onClick = {
                loading = !loading
            },
            loading = loading,
        )
    }
}
