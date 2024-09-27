package suhyang.inkspire.application.review

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.domain.book.BookRepository
import suhyang.inkspire.domain.review.Review
import suhyang.inkspire.domain.review.ReviewRepository
import suhyang.inkspire.infrastructure.review.dto.ReviewRequestDto
import suhyang.inkspire.infrastructure.review.dto.ReviewResponseDto

@RequiredArgsConstructor
@Transactional
@Service
class ReviewService(
        private val bookRepository: BookRepository,
        private val reviewRepository: ReviewRepository
) {

    fun write(
            userId: String,
            reviewCreateRequest: ReviewRequestDto.ReviewCreateRequest
    ) {
        val book:Book = bookRepository.getOneBook(reviewCreateRequest.bookId);
        val review: Review = Review(
                startPage = reviewCreateRequest.startPage,
                endPage = reviewCreateRequest.endPage,
                oneLineReview = reviewCreateRequest.oneLineReview,
                content = reviewCreateRequest.content,
                book = book
        );
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
}