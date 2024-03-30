package suhyang.inkspire.domain.user

import jakarta.persistence.*

@Entity
@Table(name = "tbl_user")
class User (

        @Id
        @Column(name = "user_id")
        val id: String,

        @Column(name = "user_name")
        val name: String,

)