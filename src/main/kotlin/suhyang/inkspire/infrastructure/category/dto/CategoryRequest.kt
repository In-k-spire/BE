package suhyang.inkspire.infrastructure.category.dto

class CategoryRequest {
    data class CreateRequest(
            val name: String
    )
    data class UpdateRequest(
            val id: Long,
            val name: String
    )

    data class PaginationRequest(
            val lastId: Long?,
            val limit: Int = 5,
    )
}