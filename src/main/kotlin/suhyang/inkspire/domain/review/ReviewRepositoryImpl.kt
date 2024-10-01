package suhyang.inkspire.domain.review

import lombok.RequiredArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.infrastructure.review.ReviewJpaRepository
import suhyang.inkspire.infrastructure.review.exception.ReviewNotFoundException
import suhyang.inkspire.infrastructure.review.projections.MonthlyReviewCountProjection
import suhyang.inkspire.infrastructure.review.projections.WeeklyReviewCountProjection

@RequiredArgsConstructor
@Component
class ReviewRepositoryImpl(
        private val reviewJpaRepository: ReviewJpaRepository
): ReviewRepository {

    override fun save(review: Review) {
        reviewJpaRepository.save(review);
    }

    override fun getOneById(reviewId: Long): Review {
        return reviewJpaRepository.findByIdOrNull(reviewId) ?: throw ReviewNotFoundException();
    }

    override fun getListByBook(book: Book): List<Review> {
        return reviewJpaRepository.findByBook(book);
    }

    override fun delete(review: Review) {
        reviewJpaRepository.delete(review);
    }

    override fun getMonthlyReviewCounts(userId: String, year: Int): List<MonthlyReviewCountProjection> {
        return reviewJpaRepository.findMonthlyReviewCountsNative(userId, year);
    }

    override fun getWeeklyReviewCounts(userId: String): List<WeeklyReviewCountProjection> {
        return reviewJpaRepository.findWeeklyReviewCounts(userId);
    }


}