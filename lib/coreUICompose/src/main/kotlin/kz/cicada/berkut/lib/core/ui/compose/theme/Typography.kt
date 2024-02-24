package kz.cicada.berkut.lib.core.ui.compose.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kz.cicada.berkut.lib.core.ui.compose.R

val StrongHubFontFamily = FontFamily(
    Font(R.font.pt_root_ui_regular),
    Font(R.font.pt_root_ui_bold, FontWeight.Bold),
    Font(R.font.pt_root_ui_light, FontWeight.Light),
    Font(R.font.pt_root_ui_medium, FontWeight.Medium),
)

val StrongHubTypography = Typography(
    h1 = TextStyle(
        fontFamily = StrongHubFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 43.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.08.sp,
    ),
    h2 = TextStyle(
        fontFamily = StrongHubFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 37.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.08.sp,
    ),
    h3 = TextStyle(
        fontFamily = StrongHubFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.08.sp,
    ),
    h4 = TextStyle(
        fontFamily = StrongHubFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.08.sp,
    ),
    h5 = TextStyle(
        fontFamily = StrongHubFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.08.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = StrongHubFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.08.sp,
    ),
    body1 = TextStyle(
        fontFamily = StrongHubFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.08.sp,
    ),
    body2 = TextStyle(
        fontFamily = StrongHubFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.08.sp,
    ),
    button = TextStyle(
        fontFamily = StrongHubFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.08.sp,
    ),
    caption = TextStyle(
        fontFamily = StrongHubFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.08.sp,
    ),
)

@Composable
fun TextStyle.bold(): TextStyle {
    return this.copy(
        fontWeight = FontWeight.Bold,
    )
}
