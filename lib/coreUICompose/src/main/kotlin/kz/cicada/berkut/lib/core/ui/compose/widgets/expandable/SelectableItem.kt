package kz.cicada.berkut.lib.core.ui.compose.widgets.expandable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.R
import kz.cicada.berkut.lib.core.ui.compose.menu.MenuLazyItemDivider
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectableItem(
    item: SelectedListItem,
    onItemClick: (SelectedListItem.SelectedItem) -> Unit,
    onDeleteClick: (SelectedListItem.SelectedItem) -> Unit,
    onMoveClick: (SelectedListItem.SelectedItem) -> Unit,
) {
    SelectableSurface { isExpanded, rotateAngle ->
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    text = item.selector,
                    style = MaterialTheme.typography.body1,
                )

                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .rotate(rotateAngle),
                    painter = painterResource(R.drawable.ic_arrow_chevron),
                    contentDescription = "Check",
                    tint = Color.Unspecified
                )
            }

            if (isExpanded) {
                item.items.forEach { value ->
                    val state = rememberDismissState(
                        confirmStateChange = {
                            if (item.movable) {
                                if (it == DismissValue.DismissedToStart) {
                                    item.items.remove(value)
                                    onDeleteClick.invoke(value)

                                } else if (it == DismissValue.DismissedToEnd) {
                                    onMoveClick.invoke(value)
                                }
                            }
                            item.movable
                        },
                    )
                    SwipeToDismiss(
                        state = state,
                        background = {
                            val color = when (state.dismissDirection) {
                                DismissDirection.StartToEnd -> Color.Green
                                DismissDirection.EndToStart -> Color.Red
                                null -> Color.Transparent
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = when (state.dismissDirection) {
                                        DismissDirection.EndToStart -> Icons.Default.Delete
                                        else -> Icons.Default.Check
                                    },
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.align(Alignment.CenterEnd)
                                )
                            }
                        },
                        dismissContent = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onItemClick.invoke(value) },
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(0.6F)
                                        .padding(
                                            start = 16.dp,
                                            end = 16.dp,
                                            bottom = 8.dp,
                                        ),
                                    style = MaterialTheme.typography.h6,
                                    text = value.name,
                                )

                                Row(
                                    modifier = Modifier.weight(0.4F),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = value.coins.toString(),
                                        style = MaterialTheme.typography.h5,
                                        color = MaterialTheme.additionalColors.elementsLowContrast,
                                    )

                                    Image(
                                        modifier = Modifier
                                            .size(44.dp)
                                            .padding(horizontal = 8.dp),
                                        painter = painterResource(R.drawable.ic_coins),
                                        contentDescription = null,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                        },
                        directions = setOf(
                            DismissDirection.StartToEnd,
                            DismissDirection.EndToStart,
                        ),
                    )
                    MenuLazyItemDivider()
                }
            }
        }
    }
}

@Composable
fun SelectableSurface(
    durationMillis: Int = 125,
    content: @Composable (isExpanded: Boolean, angle: Float) -> Unit,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var isEnabled by rememberSaveable { mutableStateOf(true) }
    var isRotatingArrow by rememberSaveable { mutableStateOf(false) }

    val angle: Float by animateFloatAsState(
        targetValue = if (isRotatingArrow) 180F else 0F,
        animationSpec = tween(
            durationMillis = durationMillis, easing = FastOutSlowInEasing
        ),
        finishedListener = { isEnabled = true },
        label = "",
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable(
                enabled = isEnabled,
                onClick = {
                    isRotatingArrow = !isRotatingArrow
                    isExpanded = !isExpanded
                    isEnabled = false
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true, radius = 3000.dp
                )
            )
    ) { content(isExpanded, angle) }
}
