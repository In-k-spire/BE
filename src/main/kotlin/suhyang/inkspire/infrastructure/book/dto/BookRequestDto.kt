package suhyang.inkspire.infrastructure.book.dto

class BookRequestDto {
    data class BookCreateRequest(
            val title: String,
            val description: String,
            val author: String,
            val shoppingLink: String,
            val imageLink: String,
            val categoryId: Long
    )
}