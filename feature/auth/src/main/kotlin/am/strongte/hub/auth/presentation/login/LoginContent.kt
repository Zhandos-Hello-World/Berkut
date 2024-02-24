package am.strongte.hub.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.lib.core.localization.string.resolve
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPlainButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.input.CommonInputField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun LoginContent(
    uiState: LoginUiState,
    controller: LoginController,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .imePadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .weight(1f),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            LoginHeader()
            Spacer(modifier = Modifier.height(16.dp))
            LoginBody(
                uiState = uiState,
                controller = controller,
                keyboardController = keyboardController,
            )
        }
        LoginFooter(
            uiState = uiState,
            controller = controller,
            keyboardController = keyboardController,
        )
    }
}

@Composable
private fun LoginHeader() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.log_in),
        style = MaterialTheme.typography.h2.copy(
            fontWeight = FontWeight.Bold,
        ),
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.welcome_message),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.additionalColors.elementsLowContrast,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LoginBody(
    uiState: LoginUiState,
    controller: LoginController,
    keyboardController: SoftwareKeyboardController?,
) {
    val focusManager = LocalFocusManager.current

    LoginEmailItem(
        uiState = uiState,
        controller = controller,
        focusManager = focusManager,
    )

    Spacer(modifier = Modifier.height(12.dp))

    LoginPasswordItem(
        uiState = uiState,
        controller = controller,
        keyboardController = keyboardController,
    )

    if (uiState.passwordError != null) {
        Spacer(modifier = Modifier.height(16.dp))
        CommonPlainButton(
            text = stringResource(id = R.string.reset_password),
            onClick = controller::onResetPasswordButtonClick,
            contentPadding = PaddingValues(vertical = 6.dp, horizontal = 8.dp),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun LoginEmailItem(
    uiState: LoginUiState,
    controller: LoginController,
    focusManager: FocusManager,
) {
    CommonInputField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { if (it.isFocused) controller.onChangeFocus(FocusedField.Email) },
        text = uiState.email,
        onValueChange = controller::onEmailFieldChange,
        labelText = stringResource(id = R.string.email),
        errorText = uiState.emailError?.resolve(),
        keyboardType = KeyboardType.Email,
        isClearIconVisible = true,
        imeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LoginPasswordItem(
    uiState: LoginUiState,
    controller: LoginController,
    keyboardController: SoftwareKeyboardController?,
) {
    CommonInputField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { if (it.isFocused) controller.onChangeFocus(FocusedField.Password) },
        text = uiState.password,
        onValueChange = controller::onPasswordFieldChange,
        labelText = stringResource(id = R.string.password),
        keyboardType = KeyboardType.Password,
        trailingIconRes = if (uiState.isPasswordVisible) {
            kz.cicada.berkut.lib.core.ui.compose.R.drawable.ic_eye_crossed_out
        } else {
            kz.cicada.berkut.lib.core.ui.compose.R.drawable.ic_eye
        },
        onClickTrailingIcon = controller::changePasswordVisibility,
        visualTransformation = if (uiState.isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation(mask = '*')
        },
        errorText = uiState.passwordError?.resolve(),
        imeAction = ImeAction.Done,
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun LoginFooter(
    uiState: LoginUiState,
    controller: LoginController,
    keyboardController: SoftwareKeyboardController?,
) {
    Column(
        Modifier
            .background(MaterialTheme.additionalColors.backgroundPrimary)
            .padding(horizontal = 16.dp),
    ) {
        CommonPrimaryButton(
            onClick = {
                keyboardController?.hide()
                controller.onLoginButtonClick()
            },
            text = stringResource(id = R.string.enter),
            loading = uiState.isEnterLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        CommonPlainButton(
            onClick = controller::onRegisterButtonClick,
            text = stringResource(id = R.string.register),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginContentPreview() {
    class FakeLoginController : LoginController {
        override fun onEmailFieldChange(email: String) = Unit
        override fun onChangeFocus(focusedField: FocusedField) = Unit
        override fun changePasswordVisibility() = Unit
        override fun onPasswordFieldChange(password: String) = Unit
        override fun onResetPasswordButtonClick() = Unit
        override fun onLoginButtonClick() = Unit
        override fun onRegisterButtonClick() = Unit
    }

    AppTheme {
        LoginContent(
            uiState = LoginUiState(
                email = "s.seifullin@strongte.am",
                password = "lmaoo",
            ),
            controller = FakeLoginController(),
        )
    }
}
