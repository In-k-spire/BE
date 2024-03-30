package suhyang.inkspire.infrastructure.auth.exception

import suhyang.inkspire.common.exception.UnauthorizedException

class ExpiredTokenException: UnauthorizedException(MESSAGE) {
    companion object {
        private const val MESSAGE = "만료된 토큰입니다."
    }
}