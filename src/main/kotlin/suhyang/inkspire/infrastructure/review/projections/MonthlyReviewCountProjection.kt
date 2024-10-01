package suhyang.inkspire.infrastructure.review.projections

interface MonthlyReviewCountProjection {
    fun getMonth(): Int;
    fun getReviewCount(): Int;
}