package kz.cicada.berkut.feature.tasks.presentation.add

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import kz.cicada.berkut.lib.core.ui.compose.widgets.button.CommonPrimaryButton
import kz.cicada.berkut.lib.core.ui.compose.widgets.input.CommonInputField
import kz.cicada.berkut.lib.core.ui.compose.widgets.toolbar.Toolbar

@Composable
fun AddTaskContent(
    controller: AddTaskController,
    uiState: AddTaskUIState.Data,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .imePadding()
            .background(MaterialTheme.additionalColors.backgroundSecondary),
    ) {
        Toolbar(
            navigateUp = controller::onNavigateBack,
            title = "Add task to children",
        )
        Spacer(modifier = Modifier.height(24.dp))
        CommonInputField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = uiState.name,
            onValueChange = controller::inputName,
            labelText = "Name of the task",
            isClearIconVisible = true,
            imeAction = ImeAction.Next,
        )

        Spacer(modifier = Modifier.height(8.dp))
        CommonInputField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = uiState.description,
            onValueChange = controller::inputDescription,
            labelText = "Description of the task",
            isClearIconVisible = true,
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.height(8.dp))
        CommonInputField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = uiState.coins,
            onValueChange = controller::inputCoins,
            labelText = "Coins",
            isClearIconVisible = true,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
            keyboardType = KeyboardType.Phone,
        )

        Spacer(modifier = Modifier.weight(1F))

        CommonPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Add number",
            onClick = controller::addTask,
        )
    }
}

@Preview
@Composable
fun AddTaskContentPreview() {
    AppTheme {
        AddTaskContent(
            controller = object : AddTaskController {
                override fun onNavigateBack() = Unit
                override fun addTask() = Unit
                override fun inputCoins(coins: String) = Unit
                override fun inputDescription(description: String) = Unit
                override fun inputName(name: String) = Unit
            },
            uiState = AddTaskUIState.Data(),
        )
    }
}