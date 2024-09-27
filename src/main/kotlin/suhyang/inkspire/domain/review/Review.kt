package suhyang.inkspire.domain.review

import jakarta.persistence.*
import suhyang.inkspire.domain.book.Book
import java.util.Date

@Entity
@Table(name = "tbl_review")
class Review(

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        val id: Long = 0,

        @Column(name = "start_page")
        val startPage: Int,

        @Column(name = "end_page")
        val endPage: Int,

        @Column(name = "one_line_reivew", length = 100)
        val oneLineReview: String,

        @Column(name = "content", length = 10000)
        val content: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "book_id")
        var book: Book,

) {
}