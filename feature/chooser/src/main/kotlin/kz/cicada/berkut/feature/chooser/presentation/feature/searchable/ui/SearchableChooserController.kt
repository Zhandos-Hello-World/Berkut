package kz.cicada.berkut.feature.chooser.presentation.feature.searchable.ui

import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo

interface SearchableChooserController {
    fun searchTextChanged(text: String)
    fun onSelectableClick(item: ChooserDvo.SelectableText)
}
