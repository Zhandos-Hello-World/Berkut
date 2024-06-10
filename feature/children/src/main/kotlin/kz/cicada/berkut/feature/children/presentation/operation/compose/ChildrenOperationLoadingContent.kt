package kz.cicada.berkut.feature.children.presentation.operation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.progress.CustomProgressBar


@Composable
fun ColumnScope.ChildrenOperationLoadingContent() {
    Spacer(modifier = Modifier.weight(1F))
    CustomProgressBar(
        modifier = Modifier
            .size(44.dp)
            .align(Alignment.CenterHorizontally),
        color = MaterialTheme.additionalColors.elementsAccent,
    )
    Spacer(modifier = Modifier.weight(1F))

}


@Preview
@Composable
fun ChildrenOperationLoadingContentPreview() {
    AppTheme {
        Column {
            ChildrenOperationLoadingContent()
        }
    }
}