package kz.cicada.berkut.lib.core.ui.compose.widgets.warning

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.cicada.berkut.lib.core.ui.compose.R

@Composable
fun WarningListCard(
    modifier: Modifier = Modifier,
    warningItems: List<String>,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.additionalColors.backgroundAccentLight1,
    contentColor: Color = MaterialTheme.additionalColors.elementsHighContrast,
    iconColor: Color = MaterialTheme.additionalColors.elementsAccent,
) {
    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = shape)
            .padding(all = 16.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_warning),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = iconColor,
        )
        Spacer(modifier = Modifier.width(8.dp))
        TextWithBulletPoints(
            contentColor = contentColor,
            items = warningItems,
        )
    }
}

@Composable
private fun TextWithBulletPoints(
    items: List<String>,
    contentColor: Color,
) {
    val bulletPoint = "  \u2022  "
    val annotatedString = buildAnnotatedString {
        items.forEach { paragraph ->
            withStyle(style = ParagraphStyle(textIndent = TextIndent(restLine = 20.sp))) {
                append(bulletPoint)
                append(paragraph)
            }
        }
    }
    Text(
        text = annotatedString,
        style = MaterialTheme.typography.body2,
        color = contentColor,
    )
}

@Preview
@Composable
private fun WarningListCardPreview() {
    AppTheme {
        WarningListCard(
            warningItems = listOf(
                "Не менее 8 символов.",
                "Как минимум одну цифру",
            ),
        )
    }
}