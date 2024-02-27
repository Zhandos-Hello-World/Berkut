package kz.cicada.berkut.feature.auth.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.auth.data.mapper.toRequest
import kz.cicada.berkut.feature.auth.data.remote.AuthApi
import kz.cicada.berkut.feature.auth.domain.model.LoginParams
import kz.cicada.berkut.feature.auth.domain.repository.AuthRepository

internal class DefaultAuthRepository(
    private val api: AuthApi,
    private val dispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun loginUser(
        params: LoginParams,
    ) {
        withContext(dispatcher) {
            val response = api.loginUser(params.toRequest())

//            dataStore.updateData { tokensPreferences ->
//                tokensPreferences.toBuilder()
//                    .setAccessToken(tokens.accessToken)
//                    .setRefreshToken(tokens.refreshToken)
//                    .build()
//            }
        }
    }
}