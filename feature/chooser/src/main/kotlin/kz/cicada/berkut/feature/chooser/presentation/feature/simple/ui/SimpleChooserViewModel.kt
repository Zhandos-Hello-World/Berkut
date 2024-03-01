package kz.cicada.berkut.feature.chooser.presentation.feature.simple.ui

import kz.cicada.berkut.feature.chooser.presentation.feature.simple.SimpleChooserLauncher
import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.base.ViewState
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

class SimpleChooserViewModel(
    private val launcher: SimpleChooserLauncher,
    private val routerFacade: RouterFacade,
) : BaseViewModel(), SimpleChooserController {
    private val _uiState = MutableStateFlow<ViewState<SimpleChooserUiState>>(
        ViewState.Data(SimpleChooserUiState.Loading),
    )

    val uiState: StateFlow<ViewState<SimpleChooserUiState>> = _uiState

    init {
        initData()
    }

    override fun onSelectableClick(item: ChooserDvo.SelectableText) {
        dataRequest(
            request = {
                launcher.behavior.onSelectableTextClick(item)
            },
            onSuccess = { events ->
                sendEvent(*events.toTypedArray())
            },
        )
    }

    override fun onSecondaryButtonClick(item: ChooserDvo.SecondaryButton) {
        dataRequest(
            request = {
                launcher.behavior.onSecondaryButtonClick(item)
            },
            onSuccess = { events ->
                sendEvent(*events.toTypedArray())
            },
        )
    }

    override fun onCloseButtonClick() {
        routerFacade.finishChain()
    }

    override fun onRepeatButtonClick() {
        initData()
    }

    private fun initData() {
        dataRequest(
            request = {
                _uiState.value = with(launcher.behavior) {
                    ViewState.Data(
                        SimpleChooserUiState.Data(
                            chooserItems = getItemList(),
                            emptyStateDetails = ChooserDvo.EmptyState,
                            chooserHeader = getHeaderOrNull(),
                            isBackButtonVisible = isCloseButtonVisible(),
                        ),
                    )
                }
            },
        )
    }
}
