package kz.cicada.berkut.feature.profile.presentation.logout

import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.ActionResultEvent

class LogoutConfirmViewModel: BaseViewModel(), LogoutConfirmController {

    override fun onLogout() {
        sendEvent(
            ActionResultEvent(
                LogoutConfirmEvent(
                    logout = true,
                )
            )
        )
    }

    override fun onCancelLogout() {
        sendEvent(
            ActionResultEvent(
                LogoutConfirmEvent(
                    logout = false,
                )
            )
        )
    }
}