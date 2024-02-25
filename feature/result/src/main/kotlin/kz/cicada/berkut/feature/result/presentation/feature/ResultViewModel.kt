package kz.cicada.berkut.feature.result.presentation.feature

import kz.cicada.berkut.lib.core.ui.base.BaseViewModel

class ResultViewModel(private val launcher: ResultLauncher) : BaseViewModel(), ResultController {
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
                sendEvent(*events.toTypedArray())
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