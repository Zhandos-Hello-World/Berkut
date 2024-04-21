package kz.cicada.berkut.feature.uploadphoto.data.repository

import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.uploadphoto.data.netowork.PhotoApi
import kz.cicada.berkut.feature.uploadphoto.domain.PhotoRepository
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import okio.source

private const val MULTIPART_FORM_DATA = "multipart/form-data"

internal class PhotoRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val userPreferences: UserPreferences,
    private val photoApi: PhotoApi,
    private val context: Context,
) : PhotoRepository {

    override suspend fun uploadPhoto(userAvatarUri: Uri) = withContext(ioDispatcher) {
        val userId = userPreferences.getId().first().toInt()
        val userName = userPreferences.getUserName().first()
        photoApi.uploadPhoto(
            id = userId,
            username = userName,
            image = createImageMultipartData(userAvatarUri),
        )
        Unit
    }

    private fun createRequestBody(value: String): RequestBody =
        value.toRequestBody(MULTIPART_FORM_DATA.toMediaTypeOrNull())

    private fun createImageMultipartData(userAvatarUri: Uri): MultipartBody.Part {
        val file = DocumentFile.fromSingleUri(context, userAvatarUri)!!
        val requestBody = object : RequestBody() {
            override fun contentType() = file.type?.toMediaTypeOrNull()
            override fun contentLength() = file.length()
            override fun writeTo(sink: BufferedSink) {
                context.contentResolver.openInputStream(userAvatarUri)
                    .use { source -> sink.writeAll(source!!.source()) }
            }
        }
        return MultipartBody.Part.createFormData(
            name = "image",
            filename = "image.jpg",
            body = requestBody,
        )
    }
}