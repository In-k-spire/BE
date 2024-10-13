package suhyang.inkspire.infrastructure.book.dto

class BookRequestDto {
    data class BookCreateRequest(
            val title: String,
            val description: String,
            val author: String,
            val publisher: String,
            val link: String,
            val image: String,
            val categoryId: Long
    )

    data class BookSearchRequest(
            val query: String,
            val display: Int = 10,
    )
}