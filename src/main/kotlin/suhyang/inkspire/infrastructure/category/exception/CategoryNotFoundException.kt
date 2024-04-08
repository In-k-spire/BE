package suhyang.inkspire.infrastructure.category.exception

import suhyang.inkspire.common.exception.NotFoundException

class CategoryNotFoundException() : NotFoundException(MESSAGE) {
    companion object {
        private const val MESSAGE: String = "존재하지 않는 Category 입니다."
    }
}