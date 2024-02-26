package kz.cicada.berkut.feature.auth.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.auth.data.remote.ValidationApi
import kz.cicada.berkut.feature.auth.data.remote.dto.VerifyPhoneRequest
import kz.cicada.berkut.feature.auth.domain.repository.ValidationRepository

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