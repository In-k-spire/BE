package suhyang.inkspire.domain.review

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import suhyang.inkspire.infrastructure.review.ReviewJpaRepository

@RequiredArgsConstructor
@Component
class ReviewRepositoryImpl(
        private val reviewJpaRepository: ReviewJpaRepository
): ReviewRepository {

    override fun save(review: Review) {
        reviewJpaRepository.save(review);
    }

}