package suhyang.inkspire.infrastructure.category

import org.springframework.data.jpa.repository.JpaRepository
import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.domain.user.User

interface CategoryJpaRepository: JpaRepository<Category, Long> {
    fun findByUser(user: User): List<Category>;
}