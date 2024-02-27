package kz.cicada.berkut.feature.language.presentation.chooser

import kz.cicada.berkut.feature.chooser.presentation.feature.simple.SimpleChooserBehavior
import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.lib.core.R
import kz.cicada.berkut.lib.core.error.handling.language.Language
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.ActionResultEvent
import kz.cicada.berkut.lib.core.ui.event.SystemEvent

@Parcelize
class LanguageChooserBehavior(private val selectedLanguageName: String) : SimpleChooserBehavior {

    override fun getHeaderOrNull(): VmRes<CharSequence> = VmRes.StrRes(R.string.language_selection)

    override suspend fun getItemList(): List<ChooserDvo> = listOf(
        ChooserDvo.SelectableText(
            id = Language.RU.name,
            text = VmRes.StrRes(R.string.russian),
            dividerVisible = false,
            isSelected = selectedLanguageName == Language.RU.name,
        ),
        ChooserDvo.SelectableText(
            id = Language.KK.name,
            text = VmRes.StrRes(R.string.kazakh),
            dividerVisible = false,
            isSelected = selectedLanguageName == Language.KK.name,
        ),
    )

    override suspend fun onSelectableTextClick(item: ChooserDvo.SelectableText): List<SystemEvent> =
        listOf(ActionResultEvent(LanguageResultEvent(Language.valueOf(item.id))))
}
