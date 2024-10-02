package suhyang.inkspire.domain.book

import jakarta.persistence.*
import suhyang.inkspire.domain.category.Category
import suhyang.inkspire.domain.review.Review
import suhyang.inkspire.domain.user.User

@Entity
@Table(name = "tbl_book")
class Book(

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        val id: Long = 0,

        @Column(name = "book_title")
        val title: String,

        @Column(name = "book_description")
        val description: String,

        @Column(name = "book_author")
        val author: String,

        @Column(name = "book_shopping_link")
        val link: String,

        @Column(name = "book_image_link")
        val image: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id")
        var category: Category,

        @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
        var reviewList: MutableList<Review> = ArrayList<Review>()

        ) {

        fun addReview(review: Review): Unit {
                this.reviewList.add(review);
        }

}