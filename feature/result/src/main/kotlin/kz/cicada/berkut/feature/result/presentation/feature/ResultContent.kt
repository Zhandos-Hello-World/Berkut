package kz.cicada.berkut.feature.result.presentation.feature

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieRetrySignal
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.localization.string.resolve
import kz.cicada.berkut.lib.core.ui.compose.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton

@Composable
internal fun ResultContent(
    uiState: ResultUiState,
    controller: ResultController,
) {
    BackHandler(
        enabled = true,
        onBack = controller::onNavigateBack,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundPrimary),
    ) {
        ComposeLottieAnimation(
            modifier = Modifier
                .weight(1F)
                .padding(top = 40.dp)
                .align(alignment = Alignment.CenterHorizontally),
            isCompleted = false,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 24.dp, end = 16.dp),
            text = uiState.title.resolve(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.additionalColors.elementsHighContrast,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 4.dp, end = 16.dp),
            text = uiState.body.resolve(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.additionalColors.elementsLowContrast,
        )

        CommonPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 94.dp, end = 16.dp, bottom = 16.dp),
            text = uiState.primaryButtonText.resolve(),
            onClick = controller::onPrimaryButtonClick,
        )
    }
}

@Composable
fun ComposeLottieAnimation(modifier: Modifier, isCompleted: Boolean) {
    val clipSpecs = LottieClipSpec.Progress(
        min = 0.0f,
        max = if (isCompleted) 0f else 1f
    )
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(kz.cicada.berkut.feature.result.R.raw.lottie))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = if (isCompleted) 1 else LottieConstants.IterateForever,
        clipSpec = clipSpecs,
    )
}

@Preview
@Composable
fun ResultContentPreview() {
    class FakeResultController : ResultController {
        override fun onPrimaryButtonClick() = Unit
        override fun onNavigateBack() = Unit
    }
    AppTheme {
        ResultContent(
            uiState = ResultUiState(
                title = VmRes.Str("Title text"),
                body = VmRes.Str("Body text"),
                primaryButtonText = VmRes.StrRes(kz.cicada.berkut.feature.result.R.string.authorize),
            ),
            controller = FakeResultController(),
        )
    }
}