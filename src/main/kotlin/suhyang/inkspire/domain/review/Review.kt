package suhyang.inkspire.domain.review

import jakarta.persistence.*
import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.domain.user.User
import java.util.Date

@Entity
@Table(name = "tbl_review")
class Review(

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        val id: Long = 0,

        @Column(name = "start_page")
        var startPage: Int,

        @Column(name = "end_page")
        var endPage: Int,

        @Column(name = "one_line_reivew", length = 100)
        var oneLineReview: String,

        @Column(name = "content", length = 10000)
        var content: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "book_id")
        var book: Book,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var user: User

) {
        fun update(startPage: Int, endPage: Int, oneLineReview: String, content: String): Unit {
                this.startPage = startPage;
                this.endPage = endPage;
                this.oneLineReview = oneLineReview;
                this.content = content;
        }
}