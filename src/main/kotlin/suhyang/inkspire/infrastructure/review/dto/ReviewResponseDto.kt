package suhyang.inkspire.infrastructure.review.dto

import suhyang.inkspire.domain.review.Review
import java.time.LocalDateTime

class ReviewResponseDto {
    data class ReviewResponse(
            val reviewId: Long,
            val startPage: Int,
            val endPage: Int,
            val oneLineReview: String,
            val content: String,
            val lastModifiedAt: LocalDateTime,
    ) {
        constructor(review: Review): this(
                reviewId = review.id,
                startPage = review.startPage,
                endPage = review.endPage,
                oneLineReview = review.oneLineReview,
                content = review.content,
                lastModifiedAt = review.modifiedAt!!,
        )
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
