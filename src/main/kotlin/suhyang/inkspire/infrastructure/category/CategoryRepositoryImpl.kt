package suhyang.inkspire.infrastructure.category

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.domain.category.CategoryRepository
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.infrastructure.category.exception.CategoryNotFoundException

@RequiredArgsConstructor
@Component
class CategoryRepositoryImpl(
        private val categoryJpaRepository: CategoryJpaRepository,
        @PersistenceContext private val entityManager: EntityManager
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

    override fun findPagingListByUser(user: User, lastId: Long?, limit: Int): List<Category> {
        val query = entityManager.createQuery("""
        select c from Category c 
        left join fetch c.bookList 
        where c.user = :user 
        and (:lastId is null or c.id < :lastId)
        order by c.id desc
    """, Category::class.java);

        query.setParameter("user", user);
        query.setParameter("lastId", lastId);
        query.setMaxResults(limit);

        return query.resultList;
    }

    override fun delete(category: Category): Unit {
        categoryJpaRepository.delete(category);
    }


}