package kz.cicada.berkut.lib.core.data.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val jwtToken: String,
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
            .addHeader(HeaderContracts.CONTENT_TYPE, "application/json")
            .apply {
                if (jwtToken.isNotEmpty()) {
                    addHeader(HeaderContracts.BEARER, "Bearer $jwtToken")
                }
            }
            .build()
        return chain.proceed(newRequest)
    }
}