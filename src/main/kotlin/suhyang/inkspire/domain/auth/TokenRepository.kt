package suhyang.inkspire.domain.auth

import java.util.Optional

interface TokenRepository {
    fun save(token: Token);
    fun findByToken(token: String): Token;
    fun findByUserId(userId: String): Optional<Token>;
    fun delete(token: Token);
}