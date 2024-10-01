package suhyang.inkspire.presentation.review

import jakarta.websocket.server.PathParam
import lombok.RequiredArgsConstructor
import org.apache.catalina.mbeans.UserMBean
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import suhyang.inkspire.application.review.ReviewService
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.infrastructure.category.dto.CategoryRequest
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

    @PutMapping("/{reviewId}")
    fun updateReview(
            @AuthenticationPrincipal loginUser: User,
            @PathVariable reviewId: Long,
            @RequestBody reviewUpdateRequest: ReviewRequestDto.ReviewUpdateRequest
    ): ResponseEntity<Unit> {
        reviewService.update(reviewId, reviewUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}")
    fun deleteReview(
            @AuthenticationPrincipal loginUser: User,
            @PathVariable reviewId: Long
    ): ResponseEntity<Unit> {
        reviewService.delete(reviewId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/month")
    fun getMonthlyReviewCounts(
            @AuthenticationPrincipal loginUser: User,
            @RequestParam year: Int
    ): ResponseEntity<List<ReviewResponseDto.MonthlyReviewCountResponse>> {
        val monthlyReview = reviewService.getMonthly(loginUser.id, year);
        return ResponseEntity.ok(monthlyReview);
    }
}