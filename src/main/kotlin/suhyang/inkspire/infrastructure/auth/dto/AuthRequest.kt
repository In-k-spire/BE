package suhyang.inkspire.infrastructure.auth.dto

data class AuthRequest(
        val tokenRequest: TokenRequest
) {
    data class TokenRequest(
            val authorizationCode: String,
            val redirectUri: String
    )
}
