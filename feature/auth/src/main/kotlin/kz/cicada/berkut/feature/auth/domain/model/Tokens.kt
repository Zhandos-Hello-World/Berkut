package kz.cicada.berkut.feature.auth.domain.model

data class Tokens(
    val accessToken: String,
    val refreshToken: String,
)