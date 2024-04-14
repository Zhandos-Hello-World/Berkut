package kz.cicada.berkut.lib.core.ui.compose.widgets.expandable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.R


data class ExpandedItem(
    val title: String,
    val answer: String
)

@Composable
fun ExpandableItem(item: ExpandedItem) {
    ExpandableSurface { isExpanded, rotateAngle ->
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    text = item.title,
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
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp,
                    ),
                    text = item.answer,
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}
@Composable
fun ExpandableSurface(durationMillis: Int = 125, content: @Composable (isExpanded: Boolean, angle: Float) -> Unit) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var isEnabled by rememberSaveable { mutableStateOf(true) }
    var isRotatingArrow by rememberSaveable { mutableStateOf(false) }

    val angle: Float by animateFloatAsState(
        targetValue = if (isRotatingArrow) 180F else 0F,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
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
                    bounded = true,
                    radius = 3000.dp
                )
            )
    ) { content(isExpanded, angle) }
}
