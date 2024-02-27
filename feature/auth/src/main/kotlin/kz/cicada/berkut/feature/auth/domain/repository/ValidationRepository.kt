package kz.cicada.berkut.feature.auth.domain.repository

internal interface ValidationRepository {
    suspend fun validatePhone(phoneNumber: String)
}