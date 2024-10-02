package suhyang.inkspire.infrastructure.book.dto

class BookRequestDto {
    data class BookCreateRequest(
            val title: String,
            val description: String,
            val author: String,
            val link: String,
            val image: String,
            val categoryId: Long
    )
}