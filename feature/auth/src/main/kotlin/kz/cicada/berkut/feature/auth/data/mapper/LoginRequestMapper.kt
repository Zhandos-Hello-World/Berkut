package kz.cicada.berkut.feature.auth.data.mapper

import kz.cicada.berkut.feature.auth.data.remote.dto.LoginRequest
import kz.cicada.berkut.feature.auth.domain.model.LoginParams

internal fun LoginParams.toRequest(
    deviceId: String,
): LoginRequest {
    return LoginRequest(
        username = this.username,
        code = this.code,
        phoneNumber = this.phoneNumber,
        role = this.userType.name,
        deviceId = deviceId,
    )
}