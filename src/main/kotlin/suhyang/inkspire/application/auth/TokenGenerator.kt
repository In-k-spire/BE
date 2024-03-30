package suhyang.inkspire.application.auth

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import suhyang.inkspire.infrastructure.auth.dto.AuthResponse

@RequiredArgsConstructor
@Component
class TokenGenerator(
        private val tokenProvider: TokenProvider
) {
    fun generateAccessAndRefreshToken(subject: String) : AuthResponse.JwtTokenResponse
    = AuthResponse.JwtTokenResponse(tokenProvider.createAccessToken(subject),
                                    tokenProvider.createRefreshToken(subject))
}