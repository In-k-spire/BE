package suhyang.inkspire.presentation.common.cookie

import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component

@Component
class CookieManager {
    fun generateCookie(name: String, value: String, maxAge: Long): ResponseCookie {
        return ResponseCookie.from(name, value)
                .httpOnly(false)
                .secure(false)
                .path("/")
                .maxAge(maxAge)
                .build()
    }
}