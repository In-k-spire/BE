package suhyang.inkspire.infrastructure.auth.exception

import suhyang.inkspire.common.exception.NotFoundException

class UserNotFoundException: NotFoundException(MESSAGE) {
    companion object {
        private const val MESSAGE: String = "유저를 찾을 수 없습니다.";
    }
}