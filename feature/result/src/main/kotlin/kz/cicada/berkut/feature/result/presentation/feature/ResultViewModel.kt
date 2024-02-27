package kz.cicada.berkut.feature.result.presentation.feature

import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

class ResultViewModel(
    private val launcher: ResultLauncher,
    private val routerFacade: RouterFacade,
) : BaseViewModel(), ResultController {
    val uiState = ResultUiState(
        title = launcher.behavior.getTitle(),
        body = launcher.behavior.getBody(),
        primaryButtonText = launcher.behavior.getPrimaryButtonText(),
    )

    override fun onPrimaryButtonClick() {
        dataRequest(
            request = {
                launcher.behavior.onPrimaryButtonClick()
            },
            onSuccess = { events ->
                events.forEach { event ->
                    val screen = (event as? OpenScreenEvent)?.screen
                    screen?.let(routerFacade::navigateTo)
                }
            },
        )
    }

    override fun onNavigateBack() {
        dataRequest(
            request = {
                launcher.behavior.onNavigateBack()
            },
            onSuccess = { events ->
                sendEvent(*events.toTypedArray())
            },
            shouldUseBasicErrorHandler = false,
        )
    }
}