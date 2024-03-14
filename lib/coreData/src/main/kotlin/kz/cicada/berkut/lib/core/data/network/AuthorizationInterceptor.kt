package kz.cicada.berkut.lib.core.data.network

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val dataStore: UserPreferences,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest =
            request.newBuilder()
                .addHeader(HeaderContracts.CONTENT_TYPE, "application/json")
                .apply {
                    addHeader(HeaderContracts.BEARER, "Bearer ${getAccessToken()}")
                }
                .build()
        return chain.proceed(newRequest)
    }

    private fun getAccessToken(): String {
        return runBlocking {
            dataStore.getJWT().first()
        }
    }
}