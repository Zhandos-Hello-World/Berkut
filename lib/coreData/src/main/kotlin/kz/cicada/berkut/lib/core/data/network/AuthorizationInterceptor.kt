package kz.cicada.berkut.lib.core.data.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
            .addHeader(HeaderContracts.CONTENT_TYPE, "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}