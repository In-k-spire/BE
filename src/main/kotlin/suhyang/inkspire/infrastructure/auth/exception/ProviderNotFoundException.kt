package suhyang.inkspire.infrastructure.auth.exception

import suhyang.inkspire.common.exception.NotFoundException


class ProviderNotFoundException() : NotFoundException(MESSAGE) {
    companion object {
        private const val MESSAGE: String = "존재하지 않는 OAuth 제공자 입니다."
    }
}