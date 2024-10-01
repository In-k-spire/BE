package suhyang.inkspire.application.review

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.domain.book.BookRepository
import suhyang.inkspire.domain.review.Review
import suhyang.inkspire.domain.review.ReviewRepository
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.domain.user.UserRepository
import suhyang.inkspire.infrastructure.review.dto.ReviewRequestDto
import suhyang.inkspire.infrastructure.review.dto.ReviewResponseDto
import suhyang.inkspire.infrastructure.review.projections.MonthlyReviewCountProjection
import suhyang.inkspire.infrastructure.review.projections.WeeklyReviewCountProjection

@RequiredArgsConstructor
@Transactional
@Service
class ReviewService(
        private val userRepository: UserRepository,
        private val bookRepository: BookRepository,
        private val reviewRepository: ReviewRepository
) {

    fun write(
            userId: String,
            reviewCreateRequest: ReviewRequestDto.ReviewCreateRequest
    ) {
        val user:User = userRepository.getUser(userId);
        val book:Book = bookRepository.getOneBook(reviewCreateRequest.bookId);
        val review: Review = Review(
                startPage = reviewCreateRequest.startPage,
                endPage = reviewCreateRequest.endPage,
                oneLineReview = reviewCreateRequest.oneLineReview,
                content = reviewCreateRequest.content,
                book = book,
                user = user
        );
        user.addReview(review);
        book.addReview(review);
        reviewRepository.save(review);
    }

    fun getList(bookId: Long): List<ReviewResponseDto.ReviewResponse> {
        val book: Book = bookRepository.getOneBook(bookId);
        val reviewList: List<Review> = reviewRepository.getListByBook(book);
        return reviewList.map {it -> ReviewResponseDto.ReviewResponse(it) }
    }

    fun update(
            reviewId: Long,
            reviewUpdateRequest: ReviewRequestDto.ReviewUpdateRequest
    ): Unit {
        val review: Review = reviewRepository.getOneById(reviewId);
        review.update(
                reviewUpdateRequest.startPage,
                reviewUpdateRequest.endPage,
                reviewUpdateRequest.oneLineReview,
                reviewUpdateRequest.content
        );
    }

    fun delete(
            reviewId: Long
    ): Unit {
        val review: Review = reviewRepository.getOneById(reviewId);
        reviewRepository.delete(review);
    }

    fun getMonthly(
            userId: String,
            year: Int
    ): List<ReviewResponseDto.MonthlyReviewCountResponse> {
        val monthlyReviewCounts: List<MonthlyReviewCountProjection> = reviewRepository.getMonthlyReviewCounts(userId, year);
        return monthlyReviewCounts.map {it -> ReviewResponseDto.MonthlyReviewCountResponse(it.getMonth(), it.getReviewCount())};
    }

    fun getWeekly(userId: String): List<ReviewResponseDto.WeeklyReviewCountResponse> {
        val weeklyReviewCounts: List<WeeklyReviewCountProjection> = reviewRepository.getWeeklyReviewCounts(userId);
        return weeklyReviewCounts.map {it -> ReviewResponseDto.WeeklyReviewCountResponse(it.getDayNumber(), it.getReviewCount())};
    }
}