package kz.cicada.berkut.lib.core.ui.compose.widgets.list

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.R

@Composable
fun TextItem(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.additionalColors.coreBlack,
    style: TextStyle = MaterialTheme.typography.body1,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.clickable(
            onClick = { onClick?.invoke() },
            interactionSource = MutableInteractionSource(),
            indication = null,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 18.dp),
            text = text,
            color = textColor,
            style = style,
        )

        Spacer(modifier = Modifier.weight(1F))

        if (selected) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_selected),
                contentDescription = null,
                tint = MaterialTheme.additionalColors.backgroundAccent,
            )
        }
    }
}

@Preview
@Composable
fun TextItemPreview() {
    AppTheme {
        TextItem(
            modifier = Modifier,
            text = "Astana",
            selected = true,
        )
    }
}
