package suhyang.inkspire.presentation.book

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import suhyang.inkspire.application.book.BookService
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.infrastructure.book.dto.BookRequestDto
import suhyang.inkspire.infrastructure.book.dto.BookResponseDto
import suhyang.inkspire.presentation.common.principal.AuthenticationPrincipal

@RequiredArgsConstructor
@RequestMapping("/api/book")
@RestController
class BookController(
        private val bookService: BookService
) {

    @PostMapping
    fun createBook(
            @AuthenticationPrincipal loginUser: User,
            @RequestBody bookCreateRequest: BookRequestDto.BookCreateRequest
    ): ResponseEntity<Unit>{
        bookService.createBook(loginUser.id, bookCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{bookId}")
    fun getOneBook(
            @AuthenticationPrincipal loginUser: User,
            @PathVariable bookId: Long
    ): ResponseEntity<BookResponseDto.BookResponse> {
        val bookResponse = bookService.getOne(bookId)
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/list/{categoryId}")
    fun getListByCategory(
            @AuthenticationPrincipal loginUser: User,
            @PathVariable categoryId: Long
    ): ResponseEntity<List<BookResponseDto.BookResponse>> {
        val bookResponseList = bookService.getList(categoryId);
        return ResponseEntity.ok(bookResponseList);
    }
}