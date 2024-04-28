package kz.cicada.berkut.push.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.push.data.network.FcmApi
import kz.cicada.berkut.push.domain.repository.PushRepository

class PushRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val fcmApi: FcmApi,
    private val userPreferences: UserPreferences,
) : PushRepository {

    override suspend fun updateFCMToken(
        tokenFCM: String,
    ) {
        return withContext(ioDispatcher) {
            userPreferences.getId().firstOrNull()?.toInt()?.let { id ->
                fcmApi.updateToken(
                    fcmToken = tokenFCM,
                    id = id,
                )
            }
        }
    }
}