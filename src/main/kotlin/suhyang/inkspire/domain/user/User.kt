package suhyang.inkspire.domain.user

import jakarta.persistence.*
import suhyang.inkspire.domain.book.Book
import suhyang.inkspire.domain.category.Category

@Entity
@Table(name = "tbl_user")
class User (

        @Id
        @Column(name = "user_id")
        val id: String,

        @Column(name = "user_name")
        var name: String,

        @OneToMany(mappedBy = "user")
        var categoryList: MutableList<Category> = ArrayList<Category>(),

        @OneToMany(mappedBy = "user")
        var bookList: MutableList<Book> = ArrayList<Book>()

) {
        fun addCategory(category: Category) {
                this.categoryList.add(category)
        }

        fun addBook(book: Book) {
                this.bookList.add(book);
        }
}