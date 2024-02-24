package kz.cicada.berkut.lib.core.ui.compose.widgets.card

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.theme.cardShape
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.cicada.berkut.lib.core.R
import kz.cicada.berkut.lib.core.ui.compose.R as composeCoreR

@Composable
fun CardWithButton(
    modifier: Modifier = Modifier,
    headText: String,
    bodyText: String,
    btnText: String = stringResource(id = R.string.confirm),
    backgroundColor: Color = MaterialTheme.additionalColors.backgroundAccentLight1,
    onClick: () -> Unit = { },
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.cardShape,
        elevation = 0.dp,
        backgroundColor = backgroundColor,
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp)
                        .weight(0.6F),
                ) {
                    Text(
                        text = headText,
                        style = MaterialTheme.typography.button.copy(
                            fontSize = 22.sp,
                        ),
                        color = MaterialTheme.additionalColors.elementsHighContrast,
                    )

                    Text(
                        text = bodyText,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.additionalColors.elementsLowContrast,
                    )
                }

                Image(
                    modifier = Modifier.weight(0.4F),
                    painter = painterResource(id = composeCoreR.drawable.bg_colleagues_card),
                    contentDescription = null,
                    alignment = Alignment.CenterEnd,
                )
            }

            CommonPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                text = btnText,
                onClick = onClick,
            )
        }
    }
}

@Preview
@Composable
fun CardWithButtonPreview() {
    AppTheme {
        CardWithButton(
            headText = "Коллеги",
            bodyText = "Не забывай как выглядят твои коллеги :)",
        )
    }
}