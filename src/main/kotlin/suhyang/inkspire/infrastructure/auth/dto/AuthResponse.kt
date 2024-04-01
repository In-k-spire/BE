package suhyang.inkspire.infrastructure.auth.dto

class AuthResponse {
    data class JwtTokenResponse(
            val accessToken: String,
            val refreshToken: String
    )

    data class ReissuedTokenResponse(
            val accessToken: String
    )

    data class URIResponse(
            val uri: String
    )
}
