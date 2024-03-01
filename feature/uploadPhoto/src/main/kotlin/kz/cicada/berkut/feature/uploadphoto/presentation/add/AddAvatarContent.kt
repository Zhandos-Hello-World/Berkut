package kz.cicada.berkut.feature.uploadphoto.presentation.add

import android.graphics.Bitmap
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.uploadPhoto.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.theme.bold
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonSecondaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
internal fun AddAvatarContent(
    uiState: AddAvatarUiState,
    controller: AddAvatarController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
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
            AddAvatarHeader()
            Spacer(modifier = Modifier.height(16.dp))
            AddAvatarBody(
                onAddAvatarButtonIconClick = controller::onAddAvatarButtonIconClick,
                avatar = uiState.avatar,
            )
            Spacer(modifier = Modifier.weight(1f))
            AddAvatarFooter(
                onContinueButtonClick = controller::onContinueButtonClick,
                onSkipButtonClick = controller::onSkipButtonClick,
            )
        }
    }
}

@Composable
private fun AddAvatarHeader() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.add_photo),
        style = MaterialTheme.typography.h2.bold(),
        color = MaterialTheme.additionalColors.elementsHighContrast,
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.do_not_be_shy_you_are_beautiful),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.additionalColors.elementsLowContrast,
    )
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
                    id = kz.cicada.berkut.lib.core.ui.compose.R.drawable.ic_camera,
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

@Composable
private fun AddAvatarFooter(
    onContinueButtonClick: () -> Unit,
    onSkipButtonClick: () -> Unit,
) {
    CommonPrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.continue_text),
        onClick = onContinueButtonClick,
    )
    Spacer(modifier = Modifier.height(12.dp))
    CommonSecondaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.skip_sad_emoji),
        onClick = onSkipButtonClick,
    )
}

@Preview(showSystemUi = true)
@Composable
fun AddAvatarContentPreview() {
    class FakeAddAvatarController : AddAvatarController {
        override fun onAddAvatarButtonIconClick() = Unit
        override fun onContinueButtonClick() = Unit
        override fun onSkipButtonClick() = Unit
        override fun onNavigateBack() = Unit
    }

    AppTheme {
        AddAvatarContent(
            uiState = AddAvatarUiState(),
            controller = FakeAddAvatarController(),
        )
    }
}