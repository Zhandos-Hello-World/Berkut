package kz.cicada.berkut.feature.profile.presentation.home.role

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.profile.presentation.home.HomeController
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonSecondaryButton

@Composable
fun ColumnScope.ProfileParentContent(
    controller: HomeController,
) {
    CommonSecondaryButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
            ),
        text = "Add Child",
        onClick = controller::navigateToScanQr,
    )
    Spacer(modifier = Modifier.height(16.dp))
    CommonSecondaryButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
            ),
        text = "Add Task",
        onClick = controller::navigateToAddTask,
    )
    Spacer(modifier = Modifier.height(16.dp))
    CommonSecondaryButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
            ),
        text = "Add Save Locations",
        onClick = controller::navigateToAddSaveLocations,
    )
    Spacer(modifier = Modifier.height(16.dp))
    CommonSecondaryButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
            ),
        text = "Add Hotline numbers",
        onClick = controller::navigateToHotlineNumbers,
    )
}

