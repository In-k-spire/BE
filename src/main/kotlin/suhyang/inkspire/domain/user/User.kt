package suhyang.inkspire.domain.user

import jakarta.persistence.*
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
        var categoryList: MutableList<Category> = ArrayList<Category>()

) {
        fun addCategory(category: Category) {
                this.categoryList.add(category)
        }
}