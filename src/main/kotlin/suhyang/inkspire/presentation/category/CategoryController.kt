package suhyang.inkspire.presentation.category

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import suhyang.inkspire.application.category.CategoryService
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.infrastructure.category.dto.CategoryRequest
import suhyang.inkspire.infrastructure.category.dto.CategoryResponseDto
import suhyang.inkspire.presentation.common.principal.AuthenticationPrincipal

@RequiredArgsConstructor
@RequestMapping("/api/category")
@RestController
class CategoryController(
        private val categoryService: CategoryService
) {

    @PostMapping
    fun createCategory(
            @AuthenticationPrincipal loginUser: User,
            @RequestBody createRequest: CategoryRequest.CreateRequest
    ): ResponseEntity<Unit> {
        categoryService.create(loginUser.id, createRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    fun updateCategory(
            @AuthenticationPrincipal loginUser: User,
            @RequestBody updateRequest: CategoryRequest.UpdateRequest
    ): ResponseEntity<Unit> {
        categoryService.update(loginUser.id, updateRequest)
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{categoryId}")
    fun getOneCategory(
            @AuthenticationPrincipal loginUser: User,
            @PathVariable categoryId: Long
    ): ResponseEntity<CategoryResponseDto.CategoryResponse> {
        val categoryResponse = categoryService.getOne(categoryId);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping
    fun getAllCategory(
            @AuthenticationPrincipal loginUser: User,

    ): ResponseEntity<List<CategoryResponseDto.CategoryResponse>> {
        val categoryResponseList = categoryService.getListByUser(loginUser);
        return ResponseEntity.ok(categoryResponseList);
    }

    @GetMapping("/page")
    fun getPagingCategory(
            @AuthenticationPrincipal loginUser: User,
            @ModelAttribute paginationRequest: CategoryRequest.PaginationRequest
    ): ResponseEntity<List<CategoryResponseDto.CategoryResponse>> {
        val categoryResponseList = categoryService.getPagingListByUser(loginUser, paginationRequest.lastId, paginationRequest.limit);
        return ResponseEntity.ok(categoryResponseList);
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(
            @AuthenticationPrincipal loginUser: User,
            @PathVariable categoryId: Long
    ): ResponseEntity<Unit> {
        categoryService.delete(loginUser.id, categoryId);
        return ResponseEntity.ok().build();
    }
}