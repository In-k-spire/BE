package suhyang.inkspire.infrastructure.review.dto

import java.util.*

class ReviewRequestDto {

    data class ReviewCreateRequest (
            val startPage: Int,
            val endPage: Int,
            val oneLineReview: String,
            val content: String,
            val bookId: Long,
    )

    data class ReviewUpdateRequest (
            val startPage: Int,
            val endPage: Int,
            val oneLineReview: String,
            val content: String
    )

}