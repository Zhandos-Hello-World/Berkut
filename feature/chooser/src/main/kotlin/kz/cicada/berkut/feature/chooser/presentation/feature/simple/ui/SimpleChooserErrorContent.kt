package kz.cicada.berkut.feature.chooser.presentation.feature.simple.ui

import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.localization.string.toVmResStr
import kz.cicada.berkut.lib.core.ui.compose.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.error.ErrorContent
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.BottomSheetToolbar

@Composable
fun SimpleChooserErrorContent(
    controller: SimpleChooserController,
    errorState: SimpleChooserUiState.Error,
) {
    Column {
        BottomSheetToolbar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            title = null,
            isBackButtonVisible = true,
            onBackClick = controller::onCloseButtonClick,
            loading = false,
        )

        ErrorContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 100.dp),
            imageResId = errorState.errorImage,
            description = errorState.errorDescription,
        )

        CommonPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
            text = stringResource(id = kz.cicada.berkut.lib.core.R.string.repeat),
            onClick = controller::onRepeatButtonClick,
        )
    }
}

@Preview
@Composable
fun SimpleChooserErrorContentPreview() {
    class FakeSimpleChooserController : SimpleChooserController {
        override fun onSelectableClick(item: ChooserDvo.SelectableText) = Unit
        override fun onSecondaryButtonClick(item: ChooserDvo.SecondaryButton) = Unit
        override fun onCloseButtonClick() = Unit
        override fun onRepeatButtonClick() = Unit
    }

    AppTheme {
        SimpleChooserErrorContent(
            controller = FakeSimpleChooserController(),
            errorState = SimpleChooserUiState.Error(
                errorImage = R.drawable.ic_error,
                errorDescription = kz.cicada.berkut.lib.core.R.string.error_from_load.toVmResStr(),
            ),
        )
    }
}