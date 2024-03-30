package suhyang.inkspire.infrastructure.auth.exception

import suhyang.inkspire.common.exception.UnauthorizedException

class EmptyAuthorizationHeaderException: UnauthorizedException(MESSAGE) {
    companion object{
        private const val MESSAGE = "Authorization Header에 값이 없습니다."
    }
}