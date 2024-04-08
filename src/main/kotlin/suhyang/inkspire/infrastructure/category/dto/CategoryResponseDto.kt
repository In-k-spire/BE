package suhyang.inkspire.infrastructure.category.dto

import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.infrastructure.book.dto.BookResponseDto

class CategoryResponseDto {
    data class CategoryResponse(
            val id: Long,
            val name: String,
            val bookList: List<BookResponseDto.BookResponse>
    ) {

        constructor(
                category: Category,
                bookList: List<BookResponseDto.BookResponse>
        ) : this(id = category.id, name = category.name, bookList = bookList);
    }
}