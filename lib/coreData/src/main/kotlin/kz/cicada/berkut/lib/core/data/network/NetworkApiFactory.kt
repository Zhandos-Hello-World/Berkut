package kz.cicada.berkut.lib.core.data.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kz.cicada.berkut.lib.core.BuildConfig
import kz.cicada.berkut.lib.core.data.UserScope
import kz.cicada.berkut.lib.core.data.error.ErrorHandlingCallAdapterFactory
import kz.cicada.berkut.lib.core.data.error.ErrorHandlingConverterFactory
import kz.cicada.berkut.lib.core.data.local.TokenPreferences
import me.nemiron.hyperion.networkemulation.NetworkEmulatorInterceptor
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT_SECONDS = 30L
private const val READ_TIMEOUT_SECONDS = 60L
private const val WRITE_TIMEOUT_SECONDS = 60L

/**
 * Создает реализации ретрофитных api
 */
@OptIn(ExperimentalSerializationApi::class)
class NetworkApiFactory(
    private val url: String,
    private val tokenPreferences: TokenPreferences,
    private val context: Context
) {

    private val chuckerCollector = ChuckerCollector(
        context = context,
        showNotification = false,
        retentionPeriod = RetentionManager.Period.ONE_HOUR,
    )

    private val json = createJson()
    private val authorizedOkHttpClient = createOkHttpClient(authorized = true)
    private val unauthorizedOkHttpClient = createOkHttpClient(authorized = false)

    private val authorizedRetrofit = createRetrofit(authorizedOkHttpClient)
    private val unauthorizedRetrofit = createRetrofit(unauthorizedOkHttpClient)

    /**
     * Создает api, для которого требуется авторизация
     */
    inline fun <reified T : Any> createAuthorizedApi(): T = createAuthorizedApi(T::class.java)

    /**
     * Создает api, для которого не требуется авторизация
     */
    inline fun <reified T : Any> createUnauthorizedApi(): T = createUnauthorizedApi(T::class.java)

    fun <T : Any> createAuthorizedApi(clazz: Class<T>): T {
        return authorizedRetrofit.create(clazz)
    }

    fun <T : Any> createUnauthorizedApi(clazz: Class<T>): T {
        return unauthorizedRetrofit.create(clazz)
    }

    private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(url).client(okHttpClient)
            .addCallAdapterFactory(ErrorHandlingCallAdapterFactory()).addConverterFactory(
                ErrorHandlingConverterFactory(
                    json.asConverterFactory(
                        "application/json".toMediaType(),
                    ),
                ),
            ).build()
    }

    private fun createOkHttpClient(authorized: Boolean): OkHttpClient {
        return OkHttpClient.Builder().apply {
                connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)

                if (authorized) {
                    addInterceptor(
                        AuthorizationInterceptor(
                            jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiaWF0IjoxNzA5NTIyODk0LCJleHAiOjE3MTA2MDI4OTQsInBob25lTnVtYmVyIjoiMjIyMjIyMjIyMyIsInJvbGUiOiJDSElMRCJ9.Zw-kRCgQS6TOvp1Fgy4eF2C6kPwEjf9qJIGxTgH69rw",
                        )
                    )
                }

                if (BuildConfig.DEBUG) {
                    addNetworkInterceptor(createLoggingInterceptor())
                    addNetworkInterceptor(createChuckerInterceptor())
                    addNetworkInterceptor(NetworkEmulatorInterceptor(context))
                }
            }.build()
    }

    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor { message ->
            Timber.tag("OkHttp").d(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun createChuckerInterceptor(): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).collector(chuckerCollector)
            .maxContentLength(250000L).redactHeaders(emptySet())
            .alwaysReadResponseBody(enable = true).build()
    }

    private fun createJson(): Json {
        return Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}