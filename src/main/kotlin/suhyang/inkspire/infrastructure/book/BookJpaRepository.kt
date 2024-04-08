package suhyang.inkspire.infrastructure.book

import org.springframework.data.jpa.repository.JpaRepository
import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.domain.user.User

interface BookJpaRepository: JpaRepository<Book, Long> {
    fun findByUser(user: User): List<Book>;
    fun findByCategory(category: Category): List<Book>;
}