package kz.cicada.berkut.push.presentation

import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.push.domain.repository.PushRepository

class PushNotificationViewModel(
    private val repo: PushRepository,
) : BaseViewModel() {

    fun sendToken(newToken: String) {
        networkRequest(
            request = {
                repo.updateFCMToken(newToken)
            },
            onError = {
                it.printStackTrace()
            },
        )
    }
}