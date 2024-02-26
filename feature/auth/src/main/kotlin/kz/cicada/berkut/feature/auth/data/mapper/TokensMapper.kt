package kz.cicada.berkut.feature.auth.data.mapper

import kz.cicada.berkut.feature.auth.data.remote.dto.LoginResponse
import kz.cicada.berkut.feature.auth.domain.model.Tokens

fun LoginResponse.toTokens(): Tokens {
    return Tokens(
        accessToken = accessToken.orEmpty(),
        refreshToken = refreshToken.orEmpty(),
    )
}
