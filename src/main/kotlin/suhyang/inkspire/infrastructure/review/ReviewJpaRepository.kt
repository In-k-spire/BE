package suhyang.inkspire.infrastructure.review

import org.springframework.data.jpa.repository.JpaRepository
import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.domain.review.Review

interface ReviewJpaRepository: JpaRepository<Review, Long> {
    fun findByBook(book: Book): List<Review>;
}