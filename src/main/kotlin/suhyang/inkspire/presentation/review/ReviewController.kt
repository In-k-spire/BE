package suhyang.inkspire.presentation.review

import jakarta.websocket.server.PathParam
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import suhyang.inkspire.application.review.ReviewService
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.infrastructure.review.dto.ReviewRequestDto
import suhyang.inkspire.infrastructure.review.dto.ReviewResponseDto
import suhyang.inkspire.presentation.common.principal.AuthenticationPrincipal

@RequiredArgsConstructor
@RequestMapping("/api/review")
@RestController
class ReviewController(
    private val reviewService: ReviewService
) {

    @PostMapping
    fun writeReview(
            @AuthenticationPrincipal loginUser: User,
            @RequestBody reviewCreateRequest: ReviewRequestDto.ReviewCreateRequest
    ): ResponseEntity<Unit> {
        reviewService.write(loginUser.id, reviewCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{bookId}")
    fun getReviewListByBook(
            @AuthenticationPrincipal loginUser: User,
            @PathVariable bookId: Long
    ): ResponseEntity<List<ReviewResponseDto.ReviewResponse>> {
        val reviewResponseList: List<ReviewResponseDto.ReviewResponse> = reviewService.getList(bookId);
        return ResponseEntity.ok(reviewResponseList);
    }
}