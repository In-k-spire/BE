package suhyang.inkspire.domain.review

import suhyang.inkspire.domain.book.Book

interface ReviewRepository {

    fun save(review: Review): Unit;
    fun getOneById(reviewId: Long): Review;
    fun getListByBook(book: Book): List<Review>;

}