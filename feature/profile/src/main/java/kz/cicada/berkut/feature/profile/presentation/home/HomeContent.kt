package kz.cicada.berkut.feature.profile.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.profile.presentation.home.role.ProfileChildContent
import kz.cicada.berkut.feature.profile.presentation.home.role.ProfileParentContent
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton

@Composable
fun HomeContent(
    controller: HomeController,
    state: HomeUIState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundPrimary),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        when (state) {
            is HomeUIState.ParentData -> ProfileParentContent(controller)
            is HomeUIState.ChildData -> ProfileChildContent(controller)
            else -> Unit
        }
        Spacer(modifier = Modifier.height(16.dp))
        CommonPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
            text = "Support",
            onClick = controller::navigateToSupport,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.additionalColors.elementsError,
            ),
            text = "Log out",
            onClick = controller::onLogoutClick,
        )
    }
}


@Preview
@Composable
fun ProfileParentContentPreview() {
    AppTheme {
        HomeContent(
            controller = object : HomeController {
                override fun navigateToHotlineNumbers() = Unit
                override fun navigateToScanQr() = Unit
                override fun navigateUp() = Unit
                override fun navigateToShareQR() = Unit
                override fun navigateToPrivacy() = Unit
                override fun navigateToSupport() = Unit
                override fun navigateToAddTask() = Unit
                override fun navigateToAddSaveLocations() = Unit
                override fun onLogoutClick() = Unit
            },
            state = HomeUIState.ParentData,
        )
    }
}

@Preview
@Composable
fun ProfileChildContentPreview() {
    AppTheme {
        HomeContent(
            controller = object : HomeController {
                override fun navigateToHotlineNumbers() = Unit
                override fun navigateToScanQr() = Unit
                override fun navigateToShareQR() = Unit
                override fun navigateUp() = Unit
                override fun navigateToPrivacy() = Unit
                override fun navigateToSupport() = Unit
                override fun navigateToAddTask() = Unit
                override fun navigateToAddSaveLocations() = Unit
                override fun onLogoutClick() = Unit
            },
            state = HomeUIState.ChildData,
        )
    }
}