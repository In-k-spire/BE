package suhyang.inkspire.infrastructure.auth

import lombok.RequiredArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import suhyang.inkspire.domain.auth.Token
import suhyang.inkspire.domain.auth.TokenRepository
import suhyang.inkspire.infrastructure.auth.exception.ExpiredTokenException
import java.util.*

@RequiredArgsConstructor
@Component
class TokenRepositoryImpl(
        private val tokenCrudRepository: TokenCrudRepository
): TokenRepository {
    override fun save(token: Token) {
        tokenCrudRepository.save(token);
    }

    override fun findByToken(token: String): Token {
        return tokenCrudRepository.findByIdOrNull(token) ?: throw ExpiredTokenException();
    }

    override fun findByUserId(userId: String): Optional<Token> {
        return tokenCrudRepository.findByUserId(userId);
    }

    override fun delete(token: Token) {
        tokenCrudRepository.delete(token);
    }
}