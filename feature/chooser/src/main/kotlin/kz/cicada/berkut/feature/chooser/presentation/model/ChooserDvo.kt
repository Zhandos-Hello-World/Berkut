package kz.cicada.berkut.feature.chooser.presentation.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import kz.cicada.berkut.lib.core.empty
import kz.cicada.berkut.lib.core.localization.string.VmRes

sealed interface ChooserDvo {

    object EmptyState : ChooserDvo

    data class TitleValue(
        val title: VmRes<CharSequence>,
        val value: @Composable () -> AnnotatedString,
        val textToHighlight: String? = null,
    ) : ChooserDvo

    data class SelectableText(
        val id: String,
        val text: VmRes<CharSequence>,
        val value: VmRes<CharSequence>? = null,
        val isSelected: Boolean,
        val dividerVisible: Boolean,
        val labeledText: VmRes<CharSequence>? = null,
        val disabledAlpha: Float = 1f,
        val isBackgroundDisabled: Boolean = false,
        val textToHighlight: String = String.empty,
        @DrawableRes val trailingIcon: Int? = null,
    ) : ChooserDvo

    data class SecondaryButton(
        val id: String,
        val text: VmRes<CharSequence>,
        val value: VmRes<CharSequence>? = null,
        val dividerVisible: Boolean,
        val labeledText: VmRes<CharSequence>? = null,
        val disabledAlpha: Float = 1f,
        val isBackgroundDisabled: Boolean = false,
        val textToHighlight: String = String.empty,
        @DrawableRes val leadingIcon: Int? = null,
    ) : ChooserDvo
}
