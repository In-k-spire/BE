package suhyang.inkspire.domain.book

import lombok.RequiredArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.infrastructure.book.BookJpaRepository
import suhyang.inkspire.infrastructure.book.exception.BookNotFoundException

@RequiredArgsConstructor
@Component
class BookRepositoryImpl(
        private val bookJpaRepository: BookJpaRepository
): BookRepository {
    override fun save(book: Book) {
        bookJpaRepository.save(book);
    }

    override fun getOneBook(bookId: Long): Book {
        return bookJpaRepository.findByIdOrNull(bookId) ?: throw BookNotFoundException();
    }

    override fun getBookList(user: User): List<Book> {
        return bookJpaRepository.findByUser(user);
    }

    override fun getBookList(category: Category): List<Book> {
        return bookJpaRepository.findByCategory(category);
    }

}