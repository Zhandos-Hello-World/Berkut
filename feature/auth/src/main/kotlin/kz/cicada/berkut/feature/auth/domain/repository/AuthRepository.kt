package kz.cicada.berkut.feature.auth.domain.repository

interface AuthRepository {
    suspend fun loginUser(email: String, password: String)
}