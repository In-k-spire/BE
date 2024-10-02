package suhyang.inkspire.infrastructure.book.dto

import suhyang.inkspire.domain.book.Book

class BookResponseDto {
    data class BookResponse (
            val title: String,
            val description: String,
            val author: String,
            val publisher: String,
            val link: String,
            val image: String
    ) {
        constructor(book: Book)
        : this(title = book.title,
                description = book.description,
                author = book.author,
                publisher = book.publisher,
                link = book.link,
                image = book.image)
    }
}