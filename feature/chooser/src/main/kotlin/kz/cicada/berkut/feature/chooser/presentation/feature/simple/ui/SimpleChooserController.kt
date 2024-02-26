package kz.cicada.berkut.feature.chooser.presentation.feature.simple.ui

import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo

interface SimpleChooserController {

    fun onSelectableClick(item: ChooserDvo.SelectableText)

    fun onSecondaryButtonClick(item: ChooserDvo.SecondaryButton)

    fun onCloseButtonClick()

    fun onRepeatButtonClick()
}
