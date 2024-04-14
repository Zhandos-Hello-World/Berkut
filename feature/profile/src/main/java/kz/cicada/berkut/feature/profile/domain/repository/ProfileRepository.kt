package kz.cicada.berkut.feature.profile.domain.repository

interface ProfileRepository {
    suspend fun updateProfile(username: String)
}