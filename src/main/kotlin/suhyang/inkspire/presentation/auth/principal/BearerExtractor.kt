package suhyang.inkspire.presentation.auth.principal

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import suhyang.inkspire.infrastructure.auth.exception.EmptyAuthorizationHeaderException
import suhyang.inkspire.infrastructure.auth.exception.InvalidTokenException
import java.util.*


open class BearerExtractor {
    companion object{
        private const val PREFIX: String = "Bearer ";

        fun extract(request: HttpServletRequest): String {
            val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
            if (authorizationHeader == null || authorizationHeader.isBlank()) {
                throw EmptyAuthorizationHeaderException()
            }
            validateAuthorizationFormat(authorizationHeader)
            return authorizationHeader.substring(PREFIX.length).trim { it <= ' ' }
        }

        private fun validateAuthorizationFormat(authorizationHeader: String) {
            if (!authorizationHeader.lowercase(Locale.getDefault()).startsWith(PREFIX.lowercase())) {
                throw InvalidTokenException()
            }
        }
    }
}