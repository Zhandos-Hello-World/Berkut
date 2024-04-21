package kz.cicada.berkut.feature.profile.presentation.support

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonSecondaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun SupportContent(
    uiState: SupportUIState,
    controller: SupportController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundPrimary),
    ) {
        Toolbar(
            navigateUp = controller::navigateUp,
            title = "Support",
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonSecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
            text = "Our mission",
            onClick = controller::navigateToOurMission,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonSecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
            text = "Privacy policy",
            onClick = controller::navigateToPrivacyPolicy,
        )
        Spacer(modifier = Modifier.height(16.dp))
        val id = (uiState as? SupportUIState.Data)?.id
        CommonSecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
            text = "Your ID: $id",
            onClick = controller::navigateToYourId,
        )
    }
}

@Preview
@Composable
fun SupportContentPreview() {
    AppTheme {
        SupportContent(
            uiState = SupportUIState.NotInit,
            controller = object : SupportController {
                override fun navigateUp() = Unit
                override fun navigateToOurMission() = Unit
                override fun navigateToPrivacyPolicy() = Unit
                override fun navigateToYourId() = Unit
            },
        )
    }
}
