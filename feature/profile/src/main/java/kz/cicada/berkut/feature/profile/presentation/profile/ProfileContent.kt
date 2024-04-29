package kz.cicada.berkut.feature.profile.presentation.profile

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.input.CommonInputField
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun ProfileContent(
    controller: ProfileController,
    uiState: ProfIleUIState.Data,
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
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            AddAvatarBody(
                onAddAvatarButtonIconClick = controller::onAddAvatarButtonIconClick,
                avatar = uiState.avatar,
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "ID: ${uiState.userId}"
            )
            Text(
                text = "Phone number: ${uiState.phoneNumber}"
            )
            Spacer(modifier = Modifier.height(16.dp))
            CommonInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
                text = uiState.username,
                onValueChange = controller::changeUsername,
                labelText = "Username",
                placeholderText = "Username",
            )
            Spacer(modifier = Modifier.weight(1f))

            CommonPrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Upload changes",
                onClick = controller::changeProfile,
                loading = uiState.loadingContinueButton,
                enabled = uiState.enabled,
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AddAvatarBody(
    onAddAvatarButtonIconClick: () -> Unit,
    avatar: Bitmap?,
) {
    Card(
        backgroundColor = MaterialTheme.additionalColors.backgroundLight,
        elevation = 0.dp,
        modifier = Modifier.size(112.dp),
        onClick = onAddAvatarButtonIconClick,
    ) {
        when (avatar) {
            null -> Icon(
                modifier = Modifier.padding(32.dp),
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.ic_camera,
                ),
                contentDescription = null,
                tint = MaterialTheme.additionalColors.elementsLowContrast,
            )

            else -> Image(
                bitmap = avatar.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview
@Composable
fun ProfileContentPreview() {
    AppTheme {
        ProfileContent(
            controller = object : ProfileController {
                override fun onNavigateBack() = Unit
                override fun changeUsername(username: String) = Unit
                override fun changeProfile() = Unit
                override fun onAddAvatarButtonIconClick() = Unit
            },
            uiState = ProfIleUIState.Data(
                userId = 2, username = "Zhasik",
                phoneNumber = "87767773954",
                loadingContinueButton = false,
                enabled = false,
            ),
        )
    }
}