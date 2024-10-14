package suhyang.inkspire.domain.category

import suhyang.inkspire.domain.user.User

interface CategoryRepository {
    fun saveCategory(category: Category): Category;
    fun findById(id: Long): Category
    fun findByUser(user: User): List<Category>;
    fun findPagingListByUser(user: User, lastId: Long?, limit: Int, name: String): List<Category>;
    fun delete(category: Category): Unit;
}