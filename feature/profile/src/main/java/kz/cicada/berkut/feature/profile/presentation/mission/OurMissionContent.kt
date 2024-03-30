package kz.cicada.berkut.feature.profile.presentation.mission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun OurMissionContent(
    onNavigateBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundPrimary),
    ) {
        Toolbar(
            navigateUp = onNavigateBack,
            title = "Our mission",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            text = """Our mission is to create a comprehensive and secure app for locating children. Our app is designed to provide a smooth and intuitive user interface, providing real-time tracking, location history and customizable alerts to inform parents of their child's location.
We prioritize privacy and security by applying robust measures to protect children's personal data and location information.
If you have questions,you can write us : berkut@gmail.com
        """.trimMargin(),
            style = MaterialTheme.typography.button,
            color = MaterialTheme.additionalColors.elementsLowContrast,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun OurMissionContentPreview() {
    AppTheme {
        OurMissionContent {}
    }
}