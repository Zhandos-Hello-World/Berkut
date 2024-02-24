package kz.cicada.berkut.lib.core.data.error

import kz.cicada.berkut.core.error.handling.DeserializationException
import retrofit2.Converter

internal class ErrorHandlingConverter<F : Any, T : Any>(private val converter: Converter<F, T>) :
    Converter<F, T> {

    override fun convert(value: F): T? {
        return try {
            converter.convert(value)
        } catch (e: Exception) {
            throw DeserializationException(e)
        }
    }
}