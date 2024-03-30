package kz.cicada.berkut.feature.profile.presentation.hotline.listHotline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme

@Composable
fun HotlineListContent(
    state: HotlineListState.Data,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(139, 35, 41))
    ) {
        state.list.forEach {
            Item(
                name = it.name,
                phoneNumber = it.phoneNumber,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun Item(
    name: String,
    phoneNumber: String,
) {
    Column {
        Text(
            text = "Name: $name",
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Phone number: $phoneNumber",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
}

@Preview
@Composable
fun HotlineListContentPreview() {
    AppTheme {
        HotlineListContent(
            state = HotlineListState.Data(
                list = listOf(
                    HotlineItem(
                        "wefew",
                        "fwefe",
                    )
                )
            ),
        )
    }
}