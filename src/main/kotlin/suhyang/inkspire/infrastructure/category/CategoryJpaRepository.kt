package suhyang.inkspire.infrastructure.category

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.domain.user.User

interface CategoryJpaRepository: JpaRepository<Category, Long> {
    @Query("select c from Category c left join fetch c.bookList join fetch c.user where c.id = :id")
    fun findByIdOrNull(id: Long): Category?;
    @Query("select c from Category c left join fetch c.bookList where c.user = :user")
    fun findByUser(user: User): List<Category>;
}