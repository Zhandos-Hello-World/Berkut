package kz.cicada.berkut.push.domain.repository

interface PushRepository {

    suspend fun updateFCMToken(
        tokenFCM: String,
    )
}