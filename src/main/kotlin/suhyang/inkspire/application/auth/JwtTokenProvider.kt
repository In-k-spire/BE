package suhyang.inkspire.application.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import suhyang.inkspire.domain.auth.Token
import suhyang.inkspire.domain.auth.TokenRepository
import suhyang.inkspire.infrastructure.auth.exception.ExpiredTokenException
import suhyang.inkspire.infrastructure.auth.exception.InvalidTokenException
import java.util.*
import javax.crypto.SecretKey

@RequiredArgsConstructor
@Component
class JwtTokenProvider(
        @Value("\${jwt.secret-key}") val secretKey: String,
        @Value("\${jwt.access-time}") val accessTime: Long,
        @Value("\${jwt.refresh-time}") val refreshTime: Long,
        private val tokenRepository: TokenRepository,
): TokenProvider {

    override fun createAccessToken(subject: String): String {
        return createToken(subject, accessTime);
    }

    override fun createRefreshToken(subject: String): String {
        val refreshToken = createToken(subject, refreshTime);
        tokenRepository.save(Token(refreshToken, subject, refreshTime));
        return refreshToken;
    }

    fun createToken(subject: String, expireTime: Long): String {
        val now: Date = Date();
        val expiration = Date(now.time + expireTime);

        return Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSecretKey(secretKey))
                .compact();
    }

    private fun getSecretKey(secretKey: String): SecretKey {
        val bytes: ByteArray = secretKey.toByteArray();
        return Keys.hmacShaKeyFor(bytes);
    }

    override fun getSubject(token: String): String {
        try{
            val jwtParser: JwtParser = Jwts.parser()
                    .verifyWith(getSecretKey(secretKey))
                    .build();
            val claims: Claims = jwtParser.parseSignedClaims(token).payload;
            return claims.subject;
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        } catch (e: JwtException) {
            throw InvalidTokenException()
        } catch (e: IllegalArgumentException) {
            throw InvalidTokenException()
        }
    }
}