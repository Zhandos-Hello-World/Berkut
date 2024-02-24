package am.strongte.hub.auth.domain.repository

interface AuthRepository {
    suspend fun loginUser(email: String, password: String)
}