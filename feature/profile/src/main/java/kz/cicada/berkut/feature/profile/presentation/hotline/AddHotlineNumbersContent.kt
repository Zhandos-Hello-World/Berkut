package kz.cicada.berkut.feature.profile.presentation.hotline

import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.core.presentation.R
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.visual.transformation.PhoneVisualTransformation
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.input.CommonInputField
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

private val PHONE_VALID_SYMBOLS = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " ")

@Composable
fun AddHotlineNumbersContent(
    uiState: AddHotlineNumberState.Data,
    controller: AddHotlineNumberController,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .imePadding()
            .background(MaterialTheme.additionalColors.backgroundPrimary),
    ) {
        Toolbar(
            navigateUp = controller::onNavigateBack,
            title = "Add hotline number"
        )
        Spacer(modifier = Modifier.height(24.dp))
        CommonInputField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = uiState.phoneNumber,
            onValueChange = controller::inputPhoneNumber,
            labelText = stringResource(R.string.phone_number),
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
        CommonInputField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = uiState.name,
            onValueChange = controller::inputNameOfHotline,
            labelText = "Имя",
            isClearIconVisible = true,
            imeAction = ImeAction.Next,
        )

        Spacer(modifier = Modifier.height(100.dp))

        CommonPrimaryButton(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            text = "Add number",
            onClick = controller::addHotlineNumber,
        )
    }
}

@Preview
@Composable
fun AddHotlineNumbersContentPreview() {
    val FakeAddHotlineNumberController = object : AddHotlineNumberController {
        override fun addHotlineNumber() = Unit
        override fun inputNameOfHotline(name: String) = Unit
        override fun inputPhoneNumber(phoneNumber: String) = Unit
        override fun onNavigateBack() = Unit
    }
    AppTheme {
        AddHotlineNumbersContent(
            uiState = AddHotlineNumberState.Data(
                "",
                "",
            ),
            controller = FakeAddHotlineNumberController,
        )
    }
}