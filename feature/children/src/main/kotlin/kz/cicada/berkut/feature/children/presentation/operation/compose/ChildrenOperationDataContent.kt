package kz.cicada.berkut.feature.children.presentation.operation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kz.cicada.berkut.feature.children.presentation.operation.ChildrenOperationController
import kz.cicada.berkut.feature.children.presentation.operation.ChildrenOperationUIState
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonSecondaryButton

@Composable
fun ColumnScope.ChildrenOperationDataContent(
    uiState: ChildrenOperationUIState.Data,
    controller: ChildrenOperationController,
) {
    Card(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Red),
    ) {
        AsyncImage(
            modifier = Modifier.size(200.dp),
            model = uiState.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }

    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = uiState.phoneNumber,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.additionalColors.elementsLowContrast,
    )

    Row(
        modifier = Modifier.padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Coins: ${uiState.coins}",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.additionalColors.elementsLowContrast,
        )
        Spacer(modifier = Modifier.size(8.dp))

        Image(
            painter = painterResource(id = kz.cicada.berkut.lib.core.ui.compose.R.drawable.ic_coins),
            contentDescription = null,
        )
    }

    Spacer(modifier = Modifier.size(16.dp))

    CommonSecondaryButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = "Task List",
        onClick = controller::onTaskListClick,
    )

    Spacer(modifier = Modifier.size(16.dp))

    CommonSecondaryButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = "Add Hotline numbers",
        onClick = controller::onAddHotlineClick,
    )
}


@Preview
@Composable
private fun ChildrenOperationDataContentPreview() {
    AppTheme {
        Column {
            ChildrenOperationDataContent(
                uiState = ChildrenOperationUIState.Data(
                    name = "Name",
                    phoneNumber = "+7 777 777 77 77",
                    imageUrl = "ImageUrl",
                    coins = "32",
                ),
                controller = object : ChildrenOperationController {
                    override fun onNavigateBack() = Unit
                    override fun onTaskListClick() = Unit
                    override fun onAddHotlineClick() = Unit
                },
            )
        }
    }
}