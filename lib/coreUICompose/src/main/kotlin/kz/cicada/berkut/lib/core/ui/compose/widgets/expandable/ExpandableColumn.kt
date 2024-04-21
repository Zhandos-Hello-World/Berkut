package kz.cicada.berkut.lib.core.ui.compose.widgets.expandable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.menu.MenuLazyItemDivider
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme


@Composable
fun ExpandableColumn(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    items: List<ExpandedItem>,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(items = items) { index, item ->
                if (index in 1 until items.size) {
                    MenuLazyItemDivider()
                }
                ExpandableItem(item = item)
            }
        }
    }
}

@Preview
@Composable
fun ExpandableColumnPreview() {
    AppTheme {
        ExpandableColumn(
            items = listOf(
                ExpandedItem(
                    title = "First question",
                    answer = "First answer",
                ),
                ExpandedItem(
                    title = "Second question",
                    answer = "Second answer",
                ),
                ExpandedItem(
                    title = "Third question",
                    answer = "Third answer",
                ),
                ExpandedItem(
                    title = "Fourth question",
                    answer = "Fourth answer",
                )
            )
        )
    }
}