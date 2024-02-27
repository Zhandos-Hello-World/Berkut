package kz.cicada.berkut.feature.auth.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kz.cicada.berkut.feature.auth.data.remote.ValidationApi
import kz.cicada.berkut.feature.auth.data.remote.dto.OtpRequest
import kz.cicada.berkut.feature.auth.domain.repository.ValidationRepository

internal class DefaultValidationRepository(
    private val api: ValidationApi,
    private val dispatcher: CoroutineDispatcher,
) : ValidationRepository {

    override suspend fun validatePhone(phoneNumber: String) {
        withContext(dispatcher) {
            api.validatePhoneNumber(
                request = OtpRequest(
                    phoneNumber = phoneNumber,
                ),
            )
        }
    }
}