package kz.cicada.berkut.feature.profile.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.profile.data.network.ProfileApi
import kz.cicada.berkut.feature.profile.domain.repository.ProfileRepository
import kz.cicada.berkut.lib.core.data.local.UserPreferences

class ProfileRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val userPreferences: UserPreferences,
    private val profileApi: ProfileApi,
) : ProfileRepository {

    override suspend fun updateProfile(username: String) {
        return withContext(ioDispatcher) {
            profileApi.updateProfile(
                id = userPreferences.getId().first().toInt(),
                username = username,
            )
        }
    }
}