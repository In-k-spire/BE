package suhyang.inkspire.infrastructure.review.projections

interface WeeklyReviewCountProjection {
    fun getDayNumber(): Int;
    fun getReviewCount(): Int;
}