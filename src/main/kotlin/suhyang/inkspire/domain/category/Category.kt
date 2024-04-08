package suhyang.inkspire.domain.category

import jakarta.persistence.*
import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.infrastructure.user.exception.DifferentUserException

@Entity
@Table(name = "tbl_category")
class Category (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "category_id")
        val id: Long = 0,

        @Column
        var name: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var user: User,

        @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
        var bookList: MutableList<Book> = ArrayList<Book>()
) {
        fun validateHasSameUser(loginUser: User) {
                if(this.user != loginUser) {
                   throw DifferentUserException()
                }
        }

        fun updateName(name: String) {
                this.name = name;
        }

        fun addBook(book: Book) {
                this.bookList.add(book);
        }
}