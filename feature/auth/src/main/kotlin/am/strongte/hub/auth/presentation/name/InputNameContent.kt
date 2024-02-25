package am.strongte.hub.auth.presentation.name

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.lib.core.empty
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.localization.string.resolve
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.input.CommonInputField
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar
import kz.cicada.berkut.lib.core.ui.compose.widgets.warning.WarningListCard

@Composable
internal fun InputNameContent(
    uiState: InputNameUiState,
    controller: InputNameController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .imePadding()
            .verticalScroll(rememberScrollState()),
    ) {
        Toolbar(
            navigateUp = controller::onNavigateBack,
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.input_name),
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
            val focusManager = LocalFocusManager.current
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.fill_field),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.additionalColors.elementsLowContrast,
            )
            Spacer(modifier = Modifier.height(12.dp))
            CommonInputField(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.username,
                onValueChange = controller::onNameFieldChanged,
                labelText = stringResource(R.string.name),
                isClearIconVisible = true,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                ),
            )
            Spacer(modifier = Modifier.height(12.dp))
            WarningListCard(
                modifier = Modifier.fillMaxWidth(),
                warningItems = uiState.warnings.map { it.resolve() },
            )
            Spacer(
                modifier = Modifier
                    .weight(1.0f)
                    .imePadding(),
            )
            CommonPrimaryButton(
                onClick = {
                    focusManager.clearFocus()
                    controller.onPrimaryButtonClick()
                },
                text = uiState.primaryButtonText.resolve(),
                loading = uiState.isPrimaryButtonLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                enabled = uiState.isPrimaryButtonEnabled,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun InputNameContentPreview() {
    class FakeInputNameController : InputNameController {
        override fun onNameFieldChanged(username: String) = Unit
        override fun onPrimaryButtonClick() = Unit
        override fun onNavigateBack() = Unit
    }

    AppTheme {
        InputNameContent(
            uiState = InputNameUiState(
                primaryButtonText = VmRes.StrRes(R.string.change),
                username = String.empty,
                warnings = listOf(
                    VmRes.StrRes(R.string.at_least_one_uppercase_and_one_lowercase_latin_letter),
                ),
            ),
            controller = FakeInputNameController(),
        )
    }
}
