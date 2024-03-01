package kz.cicada.berkut.feature.shareqr.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lightspark.composeqr.QrCodeView
import kz.cicada.berkut.feature.shareqr.presentation.ShareQRUIState
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme

@Composable
internal fun ShareQRDataContent(
    modifier: Modifier = Modifier,
    uiState: ShareQRUIState.Data,
) {
    QrCodeView(
        data = uiState.url,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun ShareQRDataContentPreview() {
    AppTheme {
        Column {
            ShareQRDataContent(
                modifier = Modifier.size(300.dp),
                uiState = ShareQRUIState.Data(
                    url = "https://www.google.com"
                ),
            )
        }
    }
}