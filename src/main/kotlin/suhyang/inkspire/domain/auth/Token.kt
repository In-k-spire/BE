package suhyang.inkspire.domain.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

@RedisHash("Token")
class Token(
        @Id
        val token: String,
        val userId: String,
        @TimeToLive
        val ttl: Long
)