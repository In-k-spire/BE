package suhyang.inkspire.infrastructure.auth.dto

data class AuthResponse(
        val jwtTokenResponse: JwtTokenResponse
) {
    data class JwtTokenResponse(
            val accessToken: String,
            val refreshToken: String
    )
}
