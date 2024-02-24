package am.strongte.hub.auth.domain.model

data class Tokens(
    val accessToken: String,
    val refreshToken: String,
)