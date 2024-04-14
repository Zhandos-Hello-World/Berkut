package kz.cicada.berkut.feature.sos.presentation.hotline.listHotline

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.sos.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.progress.CustomProgressBar
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun HotlineListContent(
    state: HotlineListState,
    controller: HotlineListController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.shadesErrorDark2)
    ) {
        Toolbar(
            navigateUp = controller::navigateUp,
            contentColor = MaterialTheme.additionalColors.coreWhite,
            navigateIcon = ImageVector.vectorResource(R.drawable.ic_close),
            iconTintColor = Color.White,
        )

        when (state) {
            is HotlineListState.Data -> {
                state.list.forEach {
                    Item(
                        name = it.name,
                        iconRes = it.iconRes,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            is HotlineListState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CustomProgressBar(
                        modifier = Modifier.size(44.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Item(
    name: String,
    @DrawableRes iconRes: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = CircleShape,
        ) {
            Icon(
                modifier = Modifier.padding(12.dp),
                imageVector = ImageVector.vectorResource(id = iconRes),
                contentDescription = null,
                tint = MaterialTheme.additionalColors.coreBlack,
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun HotlineListContentPreview() {
    AppTheme {
        HotlineListContent(
            controller = object : HotlineListController {
                override fun navigateUp() = Unit
            },
            state = HotlineListState.Loading,
        )
    }
}