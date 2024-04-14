package kz.cicada.berkut.lib.core.ui.compose.menu

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors


@Composable
fun MenuLazyItemDivider() {
    Divider(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        color = MaterialTheme.additionalColors.backgroundAccent,
        thickness = 0.5.dp
    )
}