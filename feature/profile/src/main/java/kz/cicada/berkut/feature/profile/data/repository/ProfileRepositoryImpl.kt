package kz.cicada.berkut.feature.profile.data.repository

import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.profile.data.network.ProfileApi
import kz.cicada.berkut.feature.profile.domain.repository.ProfileRepository
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source

class ProfileRepositoryImpl(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher,
    private val userPreferences: UserPreferences,
    private val profileApi: ProfileApi,
) : ProfileRepository {

    override suspend fun updateProfile(
        username: String,
        avatarUri: Uri?,
    ) {
        return withContext(ioDispatcher) {
            val id = userPreferences.getId().first().toInt()
            if (avatarUri != null) {
                profileApi.uploadPhoto(
                    id = id,
                    username = username,
                    image = createImageMultipartData(avatarUri),
                )
            } else {
                profileApi.updateProfileUserName(
                    id = id,
                    username = username,
                )
            }
        }
    }

    override suspend fun getProfile(userId: Int): String {
        return withContext(ioDispatcher) {
            profileApi.getProfile(userId)
        }
    }

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