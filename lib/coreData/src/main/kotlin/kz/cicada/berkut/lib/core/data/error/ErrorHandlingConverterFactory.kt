package kz.cicada.berkut.lib.core.data.error

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

internal class ErrorHandlingConverterFactory(private val factory: Converter.Factory) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): Converter<ResponseBody, *>? {
        val originalConverter = factory.responseBodyConverter(type, annotations, retrofit)
        return originalConverter?.let { ErrorHandlingConverter(it) }
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): Converter<*, RequestBody>? {
        val originalConverter = factory.requestBodyConverter(
            type,
            parameterAnnotations,
            methodAnnotations,
            retrofit,
        )
        return originalConverter?.let { ErrorHandlingConverter(it) }
    }
}