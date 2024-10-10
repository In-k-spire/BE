package suhyang.inkspire.presentation.common.cookie

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component

@RequiredArgsConstructor
@Component
class JwtTokenCookieGenerator(
        private val cookieManager: CookieManager,
        @Value("\${jwt.cookie.access-time}") val accessTime: Long,
        @Value("\${jwt.cookie.refresh-time}") val refreshTime: Long
) {
    fun generateAccessTokenCookie(accessToken: String): ResponseCookie {
        return cookieManager.generateCookie("accessToken", accessToken, accessTime);
    }

    fun generateRefreshTokenCookie(refreshToken: String): ResponseCookie {
        return cookieManager.generateCookie("refreshToken", refreshToken, refreshTime);
    }
}