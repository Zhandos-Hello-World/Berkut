package kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.R

@Composable
fun BigToolbar(
    modifier: Modifier,
    titleText: String,
    @DrawableRes iconResId: Int = R.drawable.ic_notifications,
) {
    Column(
        modifier = modifier,
    ) {
        Icon(
            modifier = Modifier.align(Alignment.End),
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.additionalColors.elementsAccent,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 12.dp),
            text = titleText,
            style = MaterialTheme.typography.h3.copy(
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}

@Preview
@Composable
private fun ScreenToolbarPreview() {
    AppTheme {
        BigToolbar(
            modifier = Modifier.fillMaxWidth(),
            titleText = "Главная",
        )
    }
}