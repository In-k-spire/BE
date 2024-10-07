package suhyang.inkspire.application.book

import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.domain.book.BookRepository
import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.domain.category.CategoryRepository
import suhyang.inkspire.domain.user.UserRepository
import suhyang.inkspire.infrastructure.book.dto.BookRequestDto
import suhyang.inkspire.infrastructure.book.dto.BookResponseDto

@RequiredArgsConstructor
@Transactional
@Service
class BookService(
        private val userRepository: UserRepository,
        private val categoryRepository: CategoryRepository,
        private val bookRepository: BookRepository
) {

    fun createBook(
            userId: String,
            bookCreateRequest: BookRequestDto.BookCreateRequest
    ): Unit {
        val user = userRepository.getUser(userId);
        val category = categoryRepository.findById(bookCreateRequest.categoryId);
        val book = Book(
                title = bookCreateRequest.title,
                description = bookCreateRequest.description,
                author = bookCreateRequest.author,
                publisher = bookCreateRequest.publisher,
                link = bookCreateRequest.link,
                image = bookCreateRequest.image,
                user = user,
                category = category
        );

        bookRepository.save(book);
        user.addBook(book);
        category.addBook(book);
    }

    fun getOne(bookId: Long): BookResponseDto.BookResponse {
        val book = bookRepository.getOneBook(bookId);
        return BookResponseDto.BookResponse(book);
    }

    fun getList(categoryId: Long): List<BookResponseDto.BookResponse> {
        val category: Category = categoryRepository.findById(categoryId);
        val bookList: List<Book> = bookRepository.getBookList(category);
        return bookList.map { BookResponseDto.BookResponse(it) };
    }

    fun delete(userId: String, bookId: Long): Unit {
        val user = userRepository.getUser(userId);
        val book = bookRepository.getOneBook(bookId);
        book.validateHasSameUser(user);
        bookRepository.delete(book);
    }
}