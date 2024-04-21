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

data class SelectedListItem(
    val selector: String,
    val movable: Boolean,
    val items: MutableList<SelectedItem>,
) {
    data class SelectedItem(
        val id: Int,
        val name: String,
        val coins: Int,
    )
}

@Composable
fun SelectorListColumn(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    items: MutableList<SelectedListItem>,
    onItemClick: (SelectedListItem.SelectedItem) -> Unit,
    onDeleteClick: (SelectedListItem.SelectedItem) -> Unit,
    onMoveClick: (SelectedListItem.SelectedItem) -> Unit,
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
                SelectableItem(
                    item = item,
                    onItemClick = onItemClick,
                    onDeleteClick = onDeleteClick,
                    onMoveClick = onMoveClick,
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectorListColumnPreview() {
    val items = mutableListOf(
        SelectedListItem(
            selector = "First selector",
            movable = false,
            items = mutableListOf(
                SelectedListItem.SelectedItem(
                    id = 1,
                    name = "First selector first item",
                    coins = 5,
                ), SelectedListItem.SelectedItem(
                    id = 1,
                    name = "First selector second item",
                    coins = 5,
                )
            ),
        ),
        SelectedListItem(
            selector = "Second selector",
            movable = false,
            items = mutableListOf(
                SelectedListItem.SelectedItem(
                    id = 1,
                    name = "Second selector first item",
                    coins = 5,
                )
            ),
        ),
        SelectedListItem(
            selector = "Third selector",
            movable = false,
            items = mutableListOf(
                SelectedListItem.SelectedItem(
                    id = 1,
                    name = "Third selector first item",
                    coins = 5,
                ),
            ),
        ),
    )
    AppTheme {
        SelectorListColumn(
            items = items,
            onItemClick = {},
            onDeleteClick = {},
            onMoveClick = {},
        )
    }
}