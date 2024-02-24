package kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.shimmer.ShimmerAnimation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.localization.string.resolve
import kz.cicada.berkut.lib.core.localization.string.toVmResStr
import kz.cicada.berkut.lib.core.ui.compose.R

@Composable
fun BottomSheetToolbar(
    modifier: Modifier = Modifier,
    title: VmRes<CharSequence>? = null,
    isBackButtonVisible: Boolean,
    onBackClick: () -> Unit,
    loading: Boolean = false,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (loading) {
            ShimmerAnimation { brush ->
                Box(
                    modifier = Modifier
                        .size(width = 148.dp, height = 32.dp)
                        .background(
                            shape = MaterialTheme.shapes.medium,
                            brush = brush,
                        ),
                )
            }
        } else {
            title?.let {
                Text(
                    style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.additionalColors.coreBlack,
                    text = it.resolve(),
                )
            }
        }

        Spacer(modifier = Modifier.weight(1F))

        if (isBackButtonVisible) {
            ExitButton(onClick = onBackClick)
        }
    }
}

@Composable
private fun ExitButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector = ImageVector.vectorResource(id = R.drawable.ic_exit),
) {
    IconButton(
        modifier = modifier.size(24.dp),
        onClick = onClick,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.additionalColors.elementsLowContrast,
        )
    }
}

@Preview
@Composable
private fun BottomSheetToolbarPreview() {
    AppTheme {
        BottomSheetToolbar(
            modifier = Modifier.fillMaxWidth(),
            isBackButtonVisible = true,
            title = "Choice Language".toVmResStr(),
            loading = false,
            onBackClick = { },
        )
    }
}
