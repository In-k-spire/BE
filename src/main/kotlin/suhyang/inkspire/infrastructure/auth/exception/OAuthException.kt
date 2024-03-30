package suhyang.inkspire.infrastructure.auth.exception

import suhyang.inkspire.common.exception.InternalException

class OAuthException() : InternalException(MESSAGE) {
    companion object {
        private const val MESSAGE: String = "OAuth 통신에 문제가 발생했습니다."
    }
}