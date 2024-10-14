package suhyang.inkspire.infrastructure.auth

import org.springframework.data.repository.CrudRepository
import suhyang.inkspire.domain.auth.Token
import java.util.Optional

interface TokenCrudRepository: CrudRepository<Token, String> {
    fun findByUserId(userId: String): Optional<Token>;
}