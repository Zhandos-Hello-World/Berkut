package kz.cicada.berkut.feature.profile.domain.repository

import android.net.Uri

interface ProfileRepository {
    suspend fun getProfile(userId: Int): String
    suspend fun updateProfile(username: String, avatarUri: Uri?)
}