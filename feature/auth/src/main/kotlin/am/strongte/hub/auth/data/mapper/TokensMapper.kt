package am.strongte.hub.auth.data.mapper

import am.strongte.hub.auth.data.remote.dto.LoginResponse
import am.strongte.hub.auth.domain.model.Tokens

fun LoginResponse.toTokens(): Tokens {
    return Tokens(
        accessToken = accessToken.orEmpty(),
        refreshToken = refreshToken.orEmpty(),
    )
}
