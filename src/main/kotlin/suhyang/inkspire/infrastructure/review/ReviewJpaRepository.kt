package suhyang.inkspire.infrastructure.review

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.domain.review.Review
import suhyang.inkspire.infrastructure.review.projections.MonthlyReviewCountProjection
import suhyang.inkspire.infrastructure.review.projections.WeeklyReviewCountProjection

interface ReviewJpaRepository: JpaRepository<Review, Long> {
    fun findByBook(book: Book): List<Review>;
    @Query(value = """
        SELECT
            m.month,
            COALESCE(COUNT(r.id), 0) AS reviewCount
        FROM (
            SELECT 1 AS month UNION ALL
            SELECT 2 UNION ALL
            SELECT 3 UNION ALL
            SELECT 4 UNION ALL
            SELECT 5 UNION ALL
            SELECT 6 UNION ALL
            SELECT 7 UNION ALL
            SELECT 8 UNION ALL
            SELECT 9 UNION ALL
            SELECT 10 UNION ALL
            SELECT 11 UNION ALL
            SELECT 12
        ) AS m
        LEFT JOIN tbl_review r ON MONTH(r.created_at) = m.month
        AND r.user_id = :userId
        AND YEAR(r.created_at) = :year
        GROUP BY m.month
        ORDER BY m.month
    """, nativeQuery = true)
    fun findMonthlyReviewCountsNative(
            @Param("userId") userId: String,
            @Param("year") year: Int
    ): List<MonthlyReviewCountProjection>

    @Query(value = """
        SELECT 
            n.n AS dayNumber,
            COALESCE(COUNT(r.id), 0) AS reviewCount
        FROM (
            SELECT 0 AS n UNION ALL
            SELECT 1 UNION ALL
            SELECT 2 UNION ALL
            SELECT 3 UNION ALL
            SELECT 4 UNION ALL
            SELECT 5 UNION ALL
            SELECT 6
        ) AS n
        LEFT JOIN tbl_review r ON r.user_id = :userId 
        AND DATE(r.created_at) = DATE(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY) + INTERVAL n.n DAY)
        GROUP BY dayNumber
        ORDER BY dayNumber;
    """, nativeQuery = true)
    fun findWeeklyReviewCounts(
            @Param("userId") userId: String
    ): List<WeeklyReviewCountProjection>
}