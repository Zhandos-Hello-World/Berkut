package am.strongte.hub.auth.presentation.code

import am.strongte.hub.auth.presentation.code.InputCodeConstants.DASH_CHAR
import am.strongte.hub.auth.presentation.code.InputCodeConstants.MAX_CODE_NUMBERS
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.lib.core.localization.string.resolve
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPlainButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
internal fun InputCodeContent(
    uiState: InputCodeUiState,
    controller: InputCodeController,
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        if (uiState.otpValue.length != MAX_CODE_NUMBERS) focusManager.moveFocus(FocusDirection.Next)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Toolbar(navigateUp = controller::onNavigateBack)
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.input_code),
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.code_sent_to_email),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.additionalColors.elementsLowContrast,
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputCodeField(
                otpValue = uiState.otpValue,
                onInputCodeFieldChange = controller::onInputCodeFieldChange,
                isError = uiState.codeError != null,
            )
            Spacer(modifier = Modifier.height(8.dp))
            uiState.codeError?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.resolve(),
                    color = MaterialTheme.additionalColors.elementsError,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            when {
                uiState.canSendAgain -> {
                    CommonPlainButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.send_code_again),
                        onClick = controller::onSendAgainClick,
                        contentPadding = PaddingValues(vertical = 3.dp),
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                    )
                }

                else -> {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.send_again_in_seconds_format).format(
                            uiState.secondsLeft,
                        ),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.additionalColors.elementsLowContrast,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            CommonPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = stringResource(id = R.string.confirm),
                style = MaterialTheme.typography.button,
                loading = uiState.isConfirmButtonLoading,
                onClick = controller::onConfirmClick,
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputCodeField(
    modifier: Modifier = Modifier,
    otpValue: String,
    onInputCodeFieldChange: (String) -> Unit,
    isError: Boolean = false,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = otpValue,
        onValueChange = onInputCodeFieldChange,
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(MAX_CODE_NUMBERS) { index ->
                    val char = when {
                        index >= otpValue.length -> DASH_CHAR
                        else -> otpValue[index].toString()
                    }
                    val isFocused = otpValue.length >= index
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .width(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = char,
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.Bold,
                        )
                        Divider(
                            modifier = Modifier.height(2.dp),
                            color = if (isError) {
                                MaterialTheme.additionalColors.elementsErrorLight3
                            } else if (isFocused) {
                                MaterialTheme.additionalColors.elementsAccent
                            } else {
                                MaterialTheme.additionalColors.elementsAccentLight3
                            },
                        )
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (otpValue.length == MAX_CODE_NUMBERS) keyboardController?.hide()
            },
        ),
    )
}

@Preview(showSystemUi = true)
@Composable
private fun InputCodeContentPreview() {
    class FakeInputCodeController : InputCodeController {
        override fun onInputCodeFieldChange(otpValue: String) = Unit
        override fun onSendAgainClick() = Unit
        override fun onConfirmClick() = Unit
        override fun onNavigateBack() = Unit
    }
    AppTheme {
        InputCodeContent(
            uiState = InputCodeUiState(
                "12",
            ),
            controller = FakeInputCodeController(),
        )
    }
}