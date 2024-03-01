package kz.cicada.berkut.feature.shareqr.presentation.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.shareqr.R
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.theme.bold

@Composable
internal fun ShareQRHeader() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.share_qr),
        style = MaterialTheme.typography.h2.bold(),
        color = MaterialTheme.additionalColors.elementsHighContrast,
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.dont_show_qr),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.additionalColors.elementsLowContrast,
    )
}