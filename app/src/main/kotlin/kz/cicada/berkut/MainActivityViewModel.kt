package kz.cicada.berkut

import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.OpenAuthFlowEvent

internal class MainActivityViewModel: BaseViewModel() {

    init {
        sendEvent(OpenAuthFlowEvent)
    }
}