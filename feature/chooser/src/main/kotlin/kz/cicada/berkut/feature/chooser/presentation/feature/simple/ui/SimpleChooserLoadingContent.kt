package kz.cicada.berkut.feature.chooser.presentation.feature.simple.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.widgets.shimmer.ShimmerAnimation
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.BottomSheetToolbar

private const val CHOOSER_SHIMMER_ITEM_COUNT = 8

@Composable
fun SimpleChooserLoadingContent(
    controller: SimpleChooserController,
) {
    BottomSheetToolbar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp),
        title = null,
        isBackButtonVisible = true,
        onBackClick = controller::onCloseButtonClick,
        loading = true,
    )

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        repeat(CHOOSER_SHIMMER_ITEM_COUNT) {
            ChooserShimmerItem()

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun ChooserShimmerItem() {
    ShimmerAnimation { brush ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .background(
                    shape = MaterialTheme.shapes.medium,
                    brush = brush,
                ),
        )
    }
}