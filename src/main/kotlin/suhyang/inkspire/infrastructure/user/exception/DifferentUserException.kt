package suhyang.inkspire.infrastructure.user.exception

import suhyang.inkspire.common.exception.ForbiddenException


class DifferentUserException : ForbiddenException(MESSAGE) {
    companion object {
        private const val MESSAGE: String = "다른 유저입니다."
    }
}