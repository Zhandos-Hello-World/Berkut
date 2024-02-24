package kz.cicada.berkut.lib.core.ui.compose.widgets

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopHandle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                MaterialTheme.additionalColors.backgroundDark,
                MaterialTheme.shapes.medium,
            )
            .size(32.dp, 4.dp),
    )
}

@Preview
@Composable
private fun TopHandlePreview() {
    AppTheme {
        TopHandle(
            modifier = Modifier.padding(vertical = 16.dp),
        )
    }
}
