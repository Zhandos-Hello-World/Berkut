package am.strongte.hub.auth.domain.repository

internal interface ValidationRepository {

    suspend fun validateEmail(phoneNumber: String)

    suspend fun validateCode(email: String, otpCode: String)

}