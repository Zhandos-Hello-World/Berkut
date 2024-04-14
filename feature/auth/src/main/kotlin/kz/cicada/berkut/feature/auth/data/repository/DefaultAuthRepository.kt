package kz.cicada.berkut.feature.auth.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.auth.data.mapper.toRequest
import kz.cicada.berkut.feature.auth.data.remote.AuthApi
import kz.cicada.berkut.feature.auth.domain.model.LoginParams
import kz.cicada.berkut.feature.auth.domain.repository.AuthRepository
import kz.cicada.berkut.lib.core.data.DeviceUtils
import kz.cicada.berkut.lib.core.data.local.UserPreferences

internal class DefaultAuthRepository(
    private val api: AuthApi,
    private val userPref: UserPreferences,
    private val dispatcher: CoroutineDispatcher,
    private val deviceUtils: DeviceUtils,
) : AuthRepository {

    override suspend fun loginUser(
        params: LoginParams,
    ) {
        withContext(dispatcher) {
            val response = api.loginUser(
                params.toRequest(
                    deviceId = deviceUtils.getDeviceId(),
                )
            )

            with(userPref) {
                setUserType(
                    id = response.id.toString(),
                    type = params.userType.name,
                    username = params.username,
                    jwtToken = response.jwt.orEmpty(),
                    refreshToken = response.refreshToken.orEmpty(),
                    phoneNumber = params.phoneNumber,
                )
                setAuth(isAuth = true)
            }
        }
    }
}