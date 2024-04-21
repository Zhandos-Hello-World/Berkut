package kz.cicada.berkut.feature.savedlocations.presentation.confirm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonSecondaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.input.CommonInputField

@Composable
fun SavedLocationContent(
    controller: SavedLocationsConfirmController,
    uiState: SavedLocationsConfirmUIState.Data,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                ),
            )
            .background(MaterialTheme.additionalColors.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .size(
                    width = 40.dp,
                    height = 5.dp,
                ),
            backgroundColor = MaterialTheme.additionalColors.backgroundAccent,
            content = { },
        )

        CommonInputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
            text = uiState.name,
            onValueChange = controller::onNameTextChanged,
            labelText = "Name: ",
            placeholderText = "",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_notifications),
            contentDescription = null,
            tint = Color.Red,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonSecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
            text = "Save new location",
            onClick = controller::requestSaveLocation,
        )
        Spacer(modifier = Modifier.height(24.dp))
    }

}

@Preview
@Composable
fun SavedLocationContentPreview() {
    AppTheme {
        SavedLocationContent(
            controller = object : SavedLocationsConfirmController {
                override fun requestSaveLocation() = Unit
                override fun navigateUp() = Unit
                override fun onNameTextChanged(text: String) = Unit
            },
            uiState = SavedLocationsConfirmUIState.Data(
                notify = true,
                name = "First Address",
            ),
        )
    }
}