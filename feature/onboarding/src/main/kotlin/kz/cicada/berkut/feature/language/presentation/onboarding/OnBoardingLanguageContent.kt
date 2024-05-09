package kz.cicada.berkut.feature.language.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.language.R
import kz.cicada.berkut.lib.core.error.handling.language.Language
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.input.ClickableTextField
import kz.cicada.berkut.feature.language.presentation.onboarding.OnBoardingLanguageContent as OnBoardingLanguageContent1

@Composable
internal fun OnBoardingLanguageContent(
    controller: OnBoardingLanguageController,
    uiState: OnBoardingLanguageUiState.Data,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.additionalColors.backgroundPrimary),
    ) {
        Spacer(modifier = Modifier.weight(1F))

        Image(
            modifier = Modifier
                .padding(top = 50.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = kz.cicada.berkut.core.presentation.R.drawable.ic_main),
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .padding(top = 24.dp, end = 16.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.berkut),
            style = MaterialTheme.typography.h2.copy(
                fontWeight = FontWeight.Bold,
            ),
        )

        Spacer(modifier = Modifier.weight(1F))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 4.dp, end = 16.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.information),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.additionalColors.elementsLowContrast,
            textAlign = TextAlign.Center,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 24.dp, end = 16.dp),
            text = stringResource(id = R.string.choose_language_ru),
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = MaterialTheme.additionalColors.elementsHighContrast,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 4.dp, end = 16.dp),
            text = stringResource(id = R.string.choose_language_kz),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.additionalColors.elementsLowContrast,
        )

        ClickableTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            label = stringResource(id = uiState.resId),
            onClick = controller::onLanguageClick,
        )

        CommonPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 48.dp, end = 16.dp, bottom = 16.dp),
            text = stringResource(id = R.string.confirm),
            onClick = controller::onConfirmClick,
        )
    }
}

@Preview
@Composable
fun OnBoardingLanguageContentPreview() {
    class FakeOnBoardingLanguageController : OnBoardingLanguageController {
        override fun onLanguageClick() = Unit
        override fun onConfirmClick() = Unit
    }
    AppTheme {
        OnBoardingLanguageContent1(
            controller = FakeOnBoardingLanguageController(),
            uiState = OnBoardingLanguageUiState.Data(Language.KK.resId),
        )
    }
}
