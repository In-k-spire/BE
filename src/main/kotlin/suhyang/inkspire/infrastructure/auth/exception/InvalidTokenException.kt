package suhyang.inkspire.infrastructure.auth.exception

import suhyang.inkspire.common.exception.UnauthorizedException

class InvalidTokenException: UnauthorizedException(MESSAGE) {
    companion object {
        private const val MESSAGE = "잘못된 토큰 형식입니다."
    }
}