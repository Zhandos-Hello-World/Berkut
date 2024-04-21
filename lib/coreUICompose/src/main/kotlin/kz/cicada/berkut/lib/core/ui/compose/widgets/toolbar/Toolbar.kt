package kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.R

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    navigateUp: (() -> Unit)? = null,
    navigateIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp,
    iconTintColor: Color = MaterialTheme.additionalColors.elementsAccent,
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        navigationIcon = {
            if (navigateUp != null) {
                IconButton(onClick = { navigateUp() }) {
                    Icon(
                        imageVector = navigateIcon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp),
                        tint = iconTintColor,
                    )
                }
            }
        },
        actions = actions,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
    )
}

@Preview
@Composable
private fun ToolbarPreview() {
    AppTheme {
        Toolbar()
    }
}