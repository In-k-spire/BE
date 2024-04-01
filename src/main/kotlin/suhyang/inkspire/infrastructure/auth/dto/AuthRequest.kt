package suhyang.inkspire.infrastructure.auth.dto

class AuthRequest {
    data class TokenRequest(
            val authorizationCode: String,
            val redirectUri: String
    )

    data class ReissueRequest(
            val refreshToken: String,
    )
}
