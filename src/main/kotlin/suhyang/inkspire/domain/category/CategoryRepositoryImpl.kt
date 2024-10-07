package suhyang.inkspire.domain.category

import lombok.RequiredArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.infrastructure.category.CategoryJpaRepository
import suhyang.inkspire.infrastructure.category.exception.CategoryNotFoundException

@RequiredArgsConstructor
@Component
class CategoryRepositoryImpl(
        private val categoryJpaRepository: CategoryJpaRepository
): CategoryRepository {
    override fun saveCategory(category: Category): Category {
        return categoryJpaRepository.save(category);
    }

    override fun findById(id: Long): Category {
        return categoryJpaRepository.findByIdOrNull(id) ?: throw CategoryNotFoundException();
    }

    override fun findByUser(user: User): List<Category> {
        return categoryJpaRepository.findByUser(user);
    }

    override fun delete(category: Category): Unit {
        categoryJpaRepository.delete(category);
    }


}