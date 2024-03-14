package kz.cicada.berkut.feature.auth.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.auth.data.mapper.toRequest
import kz.cicada.berkut.feature.auth.data.remote.AuthApi
import kz.cicada.berkut.feature.auth.domain.model.LoginParams
import kz.cicada.berkut.feature.auth.domain.repository.AuthRepository
import kz.cicada.berkut.lib.core.data.local.UserPreferences

internal class DefaultAuthRepository(
    private val api: AuthApi,
    private val userPref: UserPreferences,
    private val dispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun loginUser(
        params: LoginParams,
    ) {
        withContext(dispatcher) {
            val response = api.loginUser(params.toRequest())

            with(userPref) {
                setUserType(
                    type = params.userType.name,
                    username = params.username,
                    jwtToken = response.jwt.orEmpty(),
                    refreshToken = response.refreshToken.orEmpty(),
                    phoneNumber = params.phoneNumber,
                )
                setData(
                    id = response.id.toString(),
                    jwt = response.jwt.orEmpty(),
                    refreshToken = response.refreshToken.orEmpty(),
                )
                setAuth(isAuth = true)
            }
        }
    }
}