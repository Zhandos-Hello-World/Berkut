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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kz.cicada.berkut.feature.profile.R
import kz.cicada.berkut.feature.profile.presentation.home.role.ProfileChildContent
import kz.cicada.berkut.feature.profile.presentation.home.role.ProfileParentContent
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonSecondaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

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
        Toolbar(
            title = "Home",
        )
        Spacer(modifier = Modifier.height(16.dp))
        when (state) {
            is HomeUIState.ParentData -> ProfileParentContent(controller)
            is HomeUIState.ChildData -> ProfileChildContent(controller)
            else -> Unit
        }
        Spacer(modifier = Modifier.height(16.dp))

        CommonSecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
            text = "FAQ",
            onClick = controller::navigateToFAQ,
        )

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

        ComposeLottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
            isCompleted = false,
        )
    }
}

@Composable
fun ComposeLottieAnimation(modifier: Modifier, isCompleted: Boolean) {
    val clipSpecs = LottieClipSpec.Progress(
        min = 0.0f,
        max = if (isCompleted) 0f else 1f
    )
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.home_animation))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = if (isCompleted) 1 else LottieConstants.IterateForever,
        clipSpec = clipSpecs,
    )
}


@Preview
@Composable
fun ProfileParentContentPreview() {
    AppTheme {
        HomeContent(
            controller = object : HomeController {
                override fun navigateToScanQr() = Unit
                override fun navigateUp() = Unit
                override fun navigateToShareQR() = Unit
                override fun navigateToPrivacy() = Unit
                override fun navigateToSupport() = Unit
                override fun navigateToAddTask() = Unit
                override fun navigateToAddSaveLocations() = Unit
                override fun navigateToFAQ() = Unit
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
                override fun navigateToScanQr() = Unit
                override fun navigateToShareQR() = Unit
                override fun navigateUp() = Unit
                override fun navigateToPrivacy() = Unit
                override fun navigateToSupport() = Unit
                override fun navigateToAddTask() = Unit
                override fun navigateToAddSaveLocations() = Unit
                override fun navigateToFAQ() = Unit
                override fun onLogoutClick() = Unit
            },
            state = HomeUIState.ChildData,
        )
    }
}