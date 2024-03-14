package kz.cicada.berkut

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.location.external.ExternalLocationService
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel

internal class MainActivityViewModel(
    private val userPreferences: UserPreferences,
    private val dispatcher: CoroutineDispatcher,
    private val externalLocationService: ExternalLocationService,
) : BaseViewModel() {
    suspend fun isAuth(): Boolean = userPreferences.getAuth().first()

    fun checkAndRunGeoService() {
        viewModelScope.launch {
            if (userPreferences.getType().first() == UserType.CHILD.name) {
                externalLocationService.startLocationService()

                Log.d("JWTToken", userPreferences.getJWT().first())
            }
        }
    }
}