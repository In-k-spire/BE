package suhyang.inkspire.infrastructure.book.exception

import suhyang.inkspire.common.exception.NotFoundException

class BookNotFoundException : NotFoundException(MESSAGE) {
    companion object {
        private const val MESSAGE: String = "존재하지 않는 Book 입니다."
    }
}