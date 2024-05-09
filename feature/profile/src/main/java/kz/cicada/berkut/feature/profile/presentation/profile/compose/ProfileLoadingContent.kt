package kz.cicada.berkut.feature.profile.presentation.profile.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.profile.presentation.profile.ProfileController
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.progress.CustomProgressBar
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun ProfileLoadingContent(
    controller: ProfileController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(MaterialTheme.additionalColors.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Toolbar(
            navigateUp = controller::onNavigateBack,
        )
        Spacer(modifier = Modifier.weight(1F))
        CustomProgressBar(
            modifier = Modifier.size(44.dp),
            color = MaterialTheme.additionalColors.backgroundAccent,
        )
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Preview
@Composable
private fun ProfileLoadingContentPreview() {
    AppTheme {
        ProfileLoadingContent(controller = object : ProfileController {
            override fun onNavigateBack() = Unit
            override fun changeUsername(username: String) = Unit
            override fun changeProfile() = Unit
            override fun onAddAvatarButtonIconClick() = Unit
        })
    }
}