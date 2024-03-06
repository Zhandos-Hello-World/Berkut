package kz.cicada.berkut.feature.shareqr.presentation.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.shareqr.presentation.share.compose.ShareQRDataContent
import kz.cicada.berkut.feature.shareqr.presentation.share.compose.ShareQRHeader
import kz.cicada.berkut.feature.shareqr.presentation.share.compose.ShareQRLoadingContent
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
internal fun ShareQRContent(
    uiState: ShareQRUIState,
    controller: ShareQRController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        Toolbar(navigateUp = controller::onNavigateBack)
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ShareQRHeader()

            when (uiState) {
                ShareQRUIState.Loading -> {
                    ShareQRLoadingContent()
                }

                is ShareQRUIState.Data -> {
                    ShareQRDataContent(
                        modifier = Modifier
                            .size(250.dp)
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        uiState = uiState,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                }
            }

            CommonPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                text = stringResource(id = kz.cicada.berkut.core.presentation.R.string.close),
                onClick = controller::onNavigateBack,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ShareQRLoadingContentPreview() {
    class FakeShareQRController : ShareQRController {
        override fun onNavigateBack() = Unit
    }
    AppTheme {
        ShareQRContent(
            uiState = ShareQRUIState.Loading,
            controller = FakeShareQRController(),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ShareQRDataContentPreview() {
    class FakeShareQRController : ShareQRController {
        override fun onNavigateBack() = Unit
    }
    AppTheme {
        ShareQRContent(
            uiState = ShareQRUIState.Data(
                url = "https://www.google.com",
            ),
            controller = FakeShareQRController(),
        )
    }
}