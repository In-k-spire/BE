package suhyang.inkspire.application.category

import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.domain.category.CategoryRepository
import suhyang.inkspire.domain.user.UserRepository
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
            userId: String,
            categoryId: Long
    ): CategoryResponseDto.CategoryResponse {
        val user = userRepository.getUser(userId);
        val category = categoryRepository.findById(categoryId);
        return CategoryResponseDto.CategoryResponse(category);
    }

    @Transactional
    fun getList(
            userId: String,
    ): CategoryResponseDto.CategoryResponseList {
        val user = userRepository.getUser(userId);
        val categoryList: List<Category> = categoryRepository.findByUser(user);
        return CategoryResponseDto.CategoryResponseList(
                categoryList
                        .map { CategoryResponseDto.CategoryResponse(it) }
        )
    }
}