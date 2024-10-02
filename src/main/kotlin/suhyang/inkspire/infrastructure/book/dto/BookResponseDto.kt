package suhyang.inkspire.infrastructure.book.dto

import suhyang.inkspire.domain.book.Book

class BookResponseDto {
    data class BookResponse (
            val title: String,
            val description: String,
            val author: String,
            val shoppingLink: String,
            val imageLink: String
    ) {
        constructor(book: Book)
        : this(title = book.title,
                description = book.description,
                author = book.author,
                shoppingLink = book.link,
                imageLink = book.image)
    }
}