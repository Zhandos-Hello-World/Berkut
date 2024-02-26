package kz.cicada.berkut.feature.auth.presentation.input.email

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.auth.presentation.common.ProfileField
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.localization.string.resolve
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.visual.transformation.PhoneVisualTransformation
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.input.CommonInputField
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

private val PHONE_VALID_SYMBOLS = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " ")

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun InputEmailContent(
    uiState: InputEmailUiState,
    controller: InputEmailController,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .imePadding(),
    ) {
        Toolbar(
            navigateUp = controller::onNavigateBack,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = uiState.title.resolve(),
            style = MaterialTheme.typography.h2.copy(
                fontWeight = FontWeight.Bold,
            ),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = uiState.description.resolve(),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.additionalColors.elementsLowContrast,
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonInputField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = uiState.phoneNumber,
            onValueChange = {
                controller.onInputFieldChange(
                    value = it,
                    field = ProfileField.PHONE_NUMBER,
                )
            },
            labelText = stringResource(kz.cicada.berkut.core.presentation.R.string.phone_number),
            errorText = uiState.phoneNumberError?.resolve(),
            isClearIconVisible = true,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
            keyboardType = KeyboardType.Phone,
            visualTransformation = PhoneVisualTransformation(
                isKzOrRuPhone = true,
                hintColor = Color.Transparent,
            ),
            validSymbols = PHONE_VALID_SYMBOLS,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(
            modifier = Modifier
                .weight(1.0f)
                .imePadding(),
        )
        CommonPrimaryButton(
            onClick = {
                keyboardController?.hide()
                controller.onPrimaryButtonClick()
            },
            text = uiState.primaryButtonText.resolve(),
            loading = uiState.isPrimaryButtonLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            enabled = uiState.isPrimaryButtonEnabled,
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun InputEmailContentPreview() {
    class FakeInputEmailController : InputEmailController {
        override fun onPrimaryButtonClick() = Unit
        override fun onNavigateBack() = Unit
        override fun onInputFieldChange(value: String, field: ProfileField) = Unit
    }

    AppTheme {
        InputEmailContent(
            uiState = InputEmailUiState(
                isPrimaryButtonLoading = false,
                textError = null,
                title = VmRes.Str("Регистрация"),
                description = VmRes.Str("Используй свой тел номер, чтобы зарегистрироваться в системе."),
                primaryButtonText = VmRes.Str("Продолжить"),
                phoneNumber = "45675676433",
                userName = "Username",
            ),
            controller = FakeInputEmailController(),
        )
    }
}
