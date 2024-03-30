package suhyang.inkspire.infrastructure.user

import org.springframework.data.jpa.repository.JpaRepository
import suhyang.inkspire.domain.user.User
import java.util.*

interface UserJpaRepository: JpaRepository<User, String> {
    fun findByName(name: String): User?;
}