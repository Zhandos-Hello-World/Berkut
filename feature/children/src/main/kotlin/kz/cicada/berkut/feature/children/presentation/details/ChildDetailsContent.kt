package kz.cicada.berkut.feature.children.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme

@Composable
fun ChildDetailsContent(
    uiState: ChildDetailsUIState.Data,
    controller: ChildDetailsController,
) {


}

@Preview
@Composable
private fun ChildDetailsContentPreview() {
    AppTheme {
        ChildDetailsContent(
            uiState = ChildDetailsUIState.Data(
                imageUrl = "",
                username = "",
            ),
            controller = object : ChildDetailsController {},
        )
    }
}