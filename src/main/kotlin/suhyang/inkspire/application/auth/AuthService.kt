package suhyang.inkspire.application.auth

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import suhyang.inkspire.domain.user.UserRepository
import suhyang.inkspire.infrastructure.auth.dto.AuthResponse
import suhyang.inkspire.infrastructure.auth.dto.OAuthUser

@RequiredArgsConstructor
@Service
class AuthService(
        private val userRepository: UserRepository,
        private val tokenProvider: TokenProvider,
        private val tokenGenerator: TokenGenerator
) {
    fun generateJwtToken(oAuthUser: OAuthUser): AuthResponse.JwtTokenResponse {
        val user = userRepository.getUser(oAuthUser);
        return tokenGenerator.generateAccessAndRefreshToken(user.id);
    }

    fun extractId(accessToken: String): String = tokenProvider.getSubject(accessToken);

}