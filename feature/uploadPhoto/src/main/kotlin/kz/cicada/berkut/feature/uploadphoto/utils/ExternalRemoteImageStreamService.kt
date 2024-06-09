package kz.cicada.berkut.feature.uploadphoto.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.flow.first
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.BufferedInputStream
import java.io.IOException


interface ExternalRemoteImageStreamService {

    suspend fun getImage(
        url: String,
        onResponse: (Bitmap?) -> Unit,
    )
}

class DefaultExternalRemoteImageStreamService(
    private val userPreferences: UserPreferences,
) : ExternalRemoteImageStreamService {
    private var client = OkHttpClient()

    override suspend fun getImage(
        url: String,
        onResponse: (Bitmap?) -> Unit,
    ) {
        val request = Request.Builder().url(url).addHeader(
                "Authorization",
                "Bearer ${userPreferences.getJWT().first()}",
            ).get().build()

        client.newCall(request).enqueue(
            responseCallback = object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(
                    call: Call,
                    response: Response,
                ) {
                    val body = response.body
                    try {
                        val inputStream = body?.byteStream()
                        val bufferedInputStream = BufferedInputStream(inputStream)
                        val bitmap = BitmapFactory.decodeStream(bufferedInputStream)
                        onResponse.invoke(bitmap)
                    } catch (ex: Exception) {
                        onResponse.invoke(null)
                    }
                }
            },
        )
    }
}