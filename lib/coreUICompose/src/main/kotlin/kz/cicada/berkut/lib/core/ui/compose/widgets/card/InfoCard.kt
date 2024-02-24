package kz.cicada.berkut.lib.core.ui.compose.widgets.card

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.theme.cardShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    headText: String,
    subHeadText: String,
    bodyText: String,
    backgroundColor: Color = MaterialTheme.additionalColors.backgroundLight,
    onClick: () -> Unit = { },
) {
    Card(
        modifier = modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onClick,
        ),
        shape = MaterialTheme.shapes.cardShape,
        backgroundColor = backgroundColor,
        elevation = 0.dp,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = headText,
                style = MaterialTheme.typography.button,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = subHeadText,
                style = MaterialTheme.typography.button.copy(
                    fontSize = 22.sp,
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = bodyText,
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
private fun InfoCardPreview() {
    AppTheme {
        InfoCard(
            headText = "Льготы",
            bodyText = "Доступная сумма",
            subHeadText = "250 000 ₸",
        )
    }
}