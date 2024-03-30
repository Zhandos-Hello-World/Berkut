package kz.cicada.berkut.feature.uploadphoto.domain

import android.net.Uri

interface PhotoRepository {

    suspend fun uploadPhoto(userAvatarUri: Uri)

}