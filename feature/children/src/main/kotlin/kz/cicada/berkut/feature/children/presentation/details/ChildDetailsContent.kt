package kz.cicada.berkut.feature.children.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors

@Composable
fun ChildDetailsContent(
    uiState: ChildDetailsUIState.Data,
    controller: ChildDetailsController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .offset(y = 52.dp)
                .clip(CircleShape)
                .background(MaterialTheme.additionalColors.backgroundPrimary)
                ,
        ) {
            Image(
                modifier = Modifier
                    .padding(64.dp)
                    .size(50.dp),
                painter = painterResource(id = kz.cicada.berkut.lib.core.ui.compose.R.drawable.ic_coins),
                contentDescription = null,
            )

            //        AsyncImage(
//            model = uiState.imageUrl,
//            contentDescription = null,
//        )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.additionalColors.backgroundPrimary),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(52.dp))

            Text(
                text = uiState.username,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.additionalColors.elementsLowContrast,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Located on the ${uiState.location}",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.additionalColors.elementsLowContrast,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_lock_power_off),
                    contentDescription = null,
                )

                Text(
                    text = uiState.battery,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.additionalColors.elementsLowContrast,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "+7 777 111 22 234",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.additionalColors.elementsLowContrast,
            )
        }
    }
}

@Preview
@Composable
private fun ChildDetailsContentPreview() {
    AppTheme {
        ChildDetailsContent(
            uiState = ChildDetailsUIState.Data(
                imageUrl = "",
                username = "Zhandos",
                location = "Abay metro station",
                battery = "32%"
            ),
            controller = object : ChildDetailsController {},
        )
    }
}