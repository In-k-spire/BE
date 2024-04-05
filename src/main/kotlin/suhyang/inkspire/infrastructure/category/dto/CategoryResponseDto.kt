package suhyang.inkspire.infrastructure.category.dto

import suhyang.inkspire.domain.category.Category

class CategoryResponseDto {
    data class CategoryResponse(
            val id: Long,
            val name: String
    ) {

        constructor(
                category: Category
        ) : this(id = category.id, name = category.name);
    }

    data class CategoryResponseList(
            val categoryList: List<CategoryResponse>
    )
}