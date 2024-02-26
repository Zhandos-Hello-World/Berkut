package kz.cicada.berkut.feature.auth.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.auth.data.remote.AuthApi
import kz.cicada.berkut.feature.auth.data.remote.dto.LoginRequest
import kz.cicada.berkut.feature.auth.domain.repository.AuthRepository

internal class DefaultAuthRepository(
    private val api: AuthApi,
    private val dispatcher: CoroutineDispatcher,
) : AuthRepository {
    override suspend fun loginUser(email: String, password: String) {
        withContext(dispatcher) {
            val tokens = api.loginUser(
                LoginRequest(
                    email = email,
                    password = password,
                ),
            )
//            dataStore.updateData { tokensPreferences ->
//                tokensPreferences.toBuilder()
//                    .setAccessToken(tokens.accessToken)
//                    .setRefreshToken(tokens.refreshToken)
//                    .build()
//            }
        }
    }
}