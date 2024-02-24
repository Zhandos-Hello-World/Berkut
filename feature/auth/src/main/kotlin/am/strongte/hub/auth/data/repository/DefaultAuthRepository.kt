package am.strongte.hub.auth.data.repository

import am.strongte.hub.auth.data.remote.AuthApi
import am.strongte.hub.auth.data.remote.dto.LoginRequest
import am.strongte.hub.auth.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

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