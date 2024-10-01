package suhyang.inkspire.domain.review

import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.infrastructure.review.projections.MonthlyReviewCountProjection

interface ReviewRepository {

    fun save(review: Review): Unit;
    fun getOneById(reviewId: Long): Review;
    fun getListByBook(book: Book): List<Review>;
    fun delete(review: Review): Unit;
    fun getMonthlyReviewCounts(userId: String, year: Int): List<MonthlyReviewCountProjection>;

}