object Network {
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

    private const val okHttpVersion = "4.11.0"
    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"

    private const val retrofitKotlinxSerializationVersion = "1.0.0"

    const val retrofitSerialization = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$retrofitKotlinxSerializationVersion"

    val all = listOf(
        retrofit,
        moshiConverter,
        okHttp,
        okHttpLoggingInterceptor,
        retrofitSerialization,
    )
}