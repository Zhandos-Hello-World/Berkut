package kz.cicada.berkut

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel

internal class MainActivityViewModel(
    private val userPreferences: UserPreferences,
    private val dispatcher: CoroutineDispatcher,
) : BaseViewModel() {
    suspend fun isAuth(): Boolean = userPreferences.getAuth().first()

    init {
//        sendEvent(OpenAuthFlowEvent)
    }

}