package am.strongte.hub.auth.data.repository

import am.strongte.hub.auth.data.remote.ValidationApi
import am.strongte.hub.auth.data.remote.dto.VerifyPhoneRequest
import am.strongte.hub.auth.domain.repository.ValidationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DefaultValidationRepository(
    private val api: ValidationApi,
    private val dispatcher: CoroutineDispatcher,
) : ValidationRepository {

    override suspend fun validateEmail(phoneNumber: String) {
        withContext(dispatcher) {
            api.validatePhoneNumber(
                phoneNumber = phoneNumber,
            )
        }
    }

    override suspend fun validateCode(phoneNumber: String, otpCode: String) {
        withContext(dispatcher) {
            api.verifyPhoneNumber(
                VerifyPhoneRequest(
                    code = otpCode,
                    phoneNumber = phoneNumber,
                    username = "Zhandos"
                )
            )
        }
    }
}