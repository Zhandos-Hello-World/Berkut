package kz.cicada.berkut.lib.core.data.error

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kz.cicada.berkut.core.error.handling.DeserializationException
import retrofit2.HttpException
import java.nio.charset.Charset

/**
 * Данный метод позволяет смапить информацию из HttpException в модель сетевой ошибки.
 */
internal fun HttpException.getErrorResponse(): ErrorResponse? {
    return try {
        val errorMessage = this
            .response()
            ?.errorBody()
            ?.source()
            ?.readString(Charset.defaultCharset())
            .orEmpty()
        Json.decodeFromString(errorMessage)
    } catch (e: SerializationException) {
        throw DeserializationException(e)
    }
}