package kz.cicada.berkut.feature.profile.presentation.hotline.listHotline

import kotlinx.coroutines.flow.MutableStateFlow
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel

class HotlineListViewModel : BaseViewModel() {
    val state = MutableStateFlow<HotlineListState>(HotlineListState.Loading)

    fun getData() {

    }
}