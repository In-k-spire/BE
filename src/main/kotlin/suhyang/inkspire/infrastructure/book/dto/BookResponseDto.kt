package suhyang.inkspire.infrastructure.book.dto

import com.fasterxml.jackson.annotation.JsonProperty
import suhyang.inkspire.domain.book.Book

class BookResponseDto {
    data class BookResponse (
            val id: Long,
            val title: String,
            val description: String,
            val author: String,
            val publisher: String,
            val link: String,
            val image: String
    ) {
        constructor(book: Book)
        : this(id = book.id,
                title = book.title,
                description = book.description,
                author = book.author,
                publisher = book.publisher,
                link = book.link,
                image = book.image)
    }

    data class NaverClientResponse(
            @JsonProperty("items")
            val naverBookResponseList: List<NaverBookResponse>
    )

    data class NaverBookResponse(
            val title: String,
            val description: String,
            val author: String,
            val publisher: String,
            val link: String,
            val image: String
    )
}