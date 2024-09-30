package suhyang.inkspire.infrastructure.review.exception

import suhyang.inkspire.common.exception.NotFoundException

class ReviewNotFoundException() : NotFoundException(MESSAGE) {
    companion object {
        private const val MESSAGE: String = "존재하지 않는 Review 입니다."
    }
}