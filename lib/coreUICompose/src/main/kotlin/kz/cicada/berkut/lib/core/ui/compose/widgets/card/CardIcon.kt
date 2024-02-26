package kz.cicada.berkut.lib.core.ui.compose.widgets.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.theme.cardShape

@Composable
fun IconCard(
    modifier: Modifier = Modifier,
    headText: String,
    @DrawableRes iconRes: Int = R.drawable.ic_lucky_avatar,
    backgroundColor: Color = MaterialTheme.additionalColors.backgroundLight,
    selectedBackgroundColor: Color = MaterialTheme.additionalColors.backgroundAccentLight1,
    selected: Boolean = false,
    onClick: () -> Unit = { },
) {
    Card(
        modifier = modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onClick,
        ),
        shape = MaterialTheme.shapes.cardShape,
        backgroundColor = if (!selected) backgroundColor else selectedBackgroundColor,
        elevation = 4.dp,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                modifier = Modifier.weight(1F)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = iconRes),
                contentDescription = null,
            )

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally).weight(1F),
                text = headText,
                style = MaterialTheme.typography.button,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
private fun IconCardChildPreview() {
    AppTheme {
        IconCard(
            modifier = Modifier.size(100.dp),
            headText = "Ребенок",
            iconRes = R.drawable.ic_lucky_avatar,
        )
    }
}


@Preview
@Composable
private fun IconCardParentPreview() {
    AppTheme {
        IconCard(
            modifier = Modifier.size(100.dp),
            headText = "Родитель",
            iconRes = R.drawable.ic_lucky_motivating_yoda,
        )
    }
}


@Preview
@Composable
private fun IconCardEnabledPreview() {
    AppTheme {
        IconCard(
            modifier = Modifier.size(100.dp),
            headText = "Родитель",
            iconRes = R.drawable.ic_lucky_motivating_yoda,
            selected = true,
        )
    }
}

