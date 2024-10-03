package suhyang.inkspire.application.category

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.domain.category.CategoryRepository
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.domain.user.UserRepository
import suhyang.inkspire.infrastructure.book.dto.BookResponseDto
import suhyang.inkspire.infrastructure.category.dto.CategoryRequest
import suhyang.inkspire.infrastructure.category.dto.CategoryResponseDto

@RequiredArgsConstructor
@Transactional
@Service
class CategoryService(
        private val userRepository: UserRepository,
        private val categoryRepository: CategoryRepository
) {

    fun create(
            userId: String,
            createRequest: CategoryRequest.CreateRequest
    ): Unit {
        val user = userRepository.getUser(userId);
        val category: Category = Category(name = createRequest.name, user = user);
        user.addCategory(category)
        categoryRepository.saveCategory(category);
    }

    fun update(
            userId: String,
            updateRequest: CategoryRequest.UpdateRequest
    ): Unit {
        val user = userRepository.getUser(userId);
        val category = categoryRepository.findById(updateRequest.id);
        category.validateHasSameUser(user);
        category.updateName(updateRequest.name);
    }

    @Transactional
    fun getOne(
            categoryId: Long
    ): CategoryResponseDto.CategoryResponse {
        val category = categoryRepository.findById(categoryId);
        val bookListResponse = category.bookList.map { BookResponseDto.BookResponse(it) };
        return CategoryResponseDto.CategoryResponse(category, bookListResponse);
    }

    @Transactional
    fun getList(
            user: User
    ): List<CategoryResponseDto.CategoryResponse> {
        val categoryList: List<Category> = categoryRepository.findByUser(user);
        return categoryList.map { CategoryResponseDto.CategoryResponse(it, it.bookList.take(5).map {book -> BookResponseDto.BookResponse(book)}) };
    }
}