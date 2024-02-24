package kz.cicada.berkut.lib.core.ui.compose.widgets.input

import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme
import kz.cicada.berkut.lib.core.ui.compose.theme.ThemeType
import kz.cicada.berkut.lib.core.ui.compose.theme.additionalColors
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kz.cicada.berkut.lib.core.ui.compose.R

private const val EMPTY_VALUE = ""

@Composable
fun CommonInputField(
    modifier: Modifier = Modifier,
    text: String,
    isClearIconVisible: Boolean = false,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    enabled: Boolean = true,
    labelTextStyle: TextStyle = MaterialTheme.typography.body1,
    labelText: String,
    placeholderText: String? = null,
    leadingIconRes: Any? = null,
    onClickLeadingIcon: (() -> Unit)? = null,
    trailingIconRes: Any? = null,
    onClickTrailingIcon: (() -> Unit)? = null,
    errorText: String? = null,
    shape: Shape = MaterialTheme.shapes.medium.copy(CornerSize(12.dp)),
    maxLines: Int = 1,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = MaterialTheme.additionalColors.elementsHighContrast,
        backgroundColor = when {
            errorText != null -> MaterialTheme.additionalColors.backgroundErrorLight1
            else -> MaterialTheme.additionalColors.backgroundLight
        },
        disabledIndicatorColor = Color.Unspecified,
        errorIndicatorColor = Color.Unspecified,
        focusedIndicatorColor = Color.Unspecified,
        unfocusedIndicatorColor = Color.Unspecified,
        cursorColor = MaterialTheme.additionalColors.elementsAccent,
    ),
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    validSymbols: List<String>? = null,
) {
    Column(
        modifier = modifier.alpha(if (enabled) 1.0f else 0.4f),
    ) {
        var isFocused by remember { mutableStateOf(false) }

        val leadingIcon = iconGenerating(
            model = leadingIconRes,
            color = when {
                errorText != null -> MaterialTheme.additionalColors.elementsError
                else -> MaterialTheme.additionalColors.elementsLowContrast
            },
            onClick = onClickLeadingIcon,
        )
        val trailingIcon = iconGenerating(
            model = if (isClearIconVisible) R.drawable.ic_disapprove else trailingIconRes,
            color = when {
                errorText != null -> MaterialTheme.additionalColors.elementsError
                else -> MaterialTheme.additionalColors.elementsLowContrast
            },
            onClick = {
                if (isClearIconVisible) {
                    onValueChange(EMPTY_VALUE)
                } else {
                    onClickTrailingIcon?.invoke()
                }
            },
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = when {
                        errorText != null -> MaterialTheme.additionalColors.elementsError
                        isFocused -> MaterialTheme.additionalColors.elementsLowContrast
                        !enabled -> MaterialTheme.additionalColors.elementsLowContrast
                        else -> Color.Transparent
                    },
                    shape = shape,
                )
                .onFocusChanged {
                    isFocused = it.hasFocus
                },
            value = text,
            onValueChange = { newText ->
                if (validSymbols != null) {
                    onValueChange.invoke(newText.filter { validSymbols.contains(it.toString()) })
                } else {
                    onValueChange.invoke(newText)
                }
            },
            textStyle = textStyle,
            enabled = enabled,
            label = {
                CommonInputDefaultLabel(
                    text = labelText,
                    style = if (isFocused || text.isNotEmpty()) {
                        MaterialTheme.typography.body2
                    } else {
                        labelTextStyle
                    },
                    color = if (errorText != null) {
                        MaterialTheme.additionalColors.elementsError
                    } else {
                        MaterialTheme.additionalColors.elementsLowContrast
                    },
                )
            },
            placeholder = placeholderText?.let {
                {
                    CommonInputDefaultPlaceholder(
                        text = placeholderText,
                        color = when {
                            errorText != null -> MaterialTheme.additionalColors.elementsError
                            !enabled -> MaterialTheme.additionalColors.elementsLowContrast
                            else -> MaterialTheme.additionalColors.elementsHighContrast
                        },
                    )
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = if (isFocused && text.isNotEmpty()) trailingIcon else null,
            isError = errorText != null,
            shape = shape,
            maxLines = maxLines,
            colors = colors,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            visualTransformation = if (isFocused || text.isNotEmpty()) visualTransformation else VisualTransformation.None,
            keyboardActions = keyboardActions,
        )
        if (!errorText.isNullOrBlank()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 16.dp),
                text = errorText,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.additionalColors.elementsError,
            )
        }
    }
}

@Composable
private fun iconGenerating(
    modifier: Modifier = Modifier,
    model: Any?,
    color: Color = MaterialTheme.additionalColors.coreBlack,
    onClick: (() -> Unit)? = null,
): @Composable (() -> Unit)? {
    if (model == null) return null

    return {
        CommonInputIcon(
            modifier = modifier,
            model = model,
            color = color,
            onClick = onClick,
        )
    }
}

@Composable
private fun CommonInputIcon(
    modifier: Modifier = Modifier,
    model: Any?,
    color: Color = MaterialTheme.additionalColors.coreBlack,
    onClick: (() -> Unit)? = null,
) {
    IconButton(onClick = { onClick?.invoke() }, enabled = onClick != null) {
        AsyncImage(
            model = model,
            contentDescription = null,
            modifier = modifier.size(24.dp),
            colorFilter = ColorFilter.tint(color),
        )
    }
}

@Composable
private fun CommonInputDefaultLabel(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.body2,
    color: Color = MaterialTheme.additionalColors.coreBlack,
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color,
    )
}

@Composable
fun CommonInputDefaultPlaceholder(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.body1,
    color: Color = MaterialTheme.additionalColors.coreBlack,
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color,
    )
}

@Preview
@Composable
fun CommonInputFieldPreview() {
    var text by remember { mutableStateOf("") }
    AppTheme(themeType = ThemeType.LIGHT_THEME) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            CommonInputField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = text,
                labelText = "Наименование поля",
                onValueChange = { text = it },
                placeholderText = "Placeholder",
            )

            CommonInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = text,
                labelText = "Наименование поля",
                onValueChange = { text = it },
                placeholderText = "Placeholder",
                errorText = "Alright",
            )

            CommonInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = "Hello",
                labelText = "Наименование поля",
                onValueChange = { },
                placeholderText = "Placeholder",
                enabled = false,
            )

            CommonInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = "Hello",
                labelText = "Наименование поля",
                onValueChange = { },
                placeholderText = "Placeholder",
                leadingIconRes = R.drawable.ic_back,
                trailingIconRes = R.drawable.ic_back,
            )

            CommonInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = "Hello",
                labelText = "Наименование поля",
                onValueChange = { },
                placeholderText = "Placeholder",
                leadingIconRes = R.drawable.ic_back,
                trailingIconRes = R.drawable.ic_back,
                errorText = "Well...",
            )
        }
    }
}
