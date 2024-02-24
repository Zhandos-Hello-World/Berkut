package kz.cicada.berkut.lib.core.ui.compose.widgets.error

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.localization.string.resolve
import kz.cicada.berkut.lib.core.localization.string.toVmResStr
import kz.cicada.berkut.lib.core.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.R as composeCoreR

@Composable
fun ErrorContent(
    modifier: Modifier,
    @DrawableRes imageResId: Int = composeCoreR.drawable.ic_error,
    description: VmRes<CharSequence>? = R.string.error_from_load.toVmResStr(),
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = imageResId),
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            text = stringResource(id = R.string.error_title),
            style = MaterialTheme.typography.button.copy(
                fontSize = 18.sp,
            ),
            textAlign = TextAlign.Center,
        )

        description?.let { text ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = text.resolve(),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    AppTheme {
        Column(
            modifier = Modifier.background(MaterialTheme.colors.background),
        ) {
            ErrorContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 100.dp),
            )
        }
    }
}