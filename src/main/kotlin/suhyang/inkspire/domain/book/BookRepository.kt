package suhyang.inkspire.domain.book

import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.domain.user.User

interface BookRepository {
    fun save(book: Book): Unit;
    fun getOneBook(bookId: Long): Book;
    fun getBookList(user: User): List<Book>;
    fun getBookList(category: Category): List<Book>;
}