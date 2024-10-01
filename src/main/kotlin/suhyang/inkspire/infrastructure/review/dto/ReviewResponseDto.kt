package suhyang.inkspire.infrastructure.review.dto

import suhyang.inkspire.domain.review.Review

class ReviewResponseDto {
    data class ReviewResponse(
            val startPage: Int,
            val endPage: Int,
            val oneLineReview: String,
            val content: String
    ) {
        constructor(review: Review): this(
                startPage = review.startPage,
                endPage = review.endPage,
                oneLineReview = review.oneLineReview,
                content = review.content)
    }

    data class MonthlyReviewCountResponse(
            val month: Int,
            val count: Int
    )

    data class WeeklyReviewCountResponse(
            val dayNumber: Int,
            val count: Int
    )
}
