package suhyang.inkspire.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Configuration
class RedisConfig(
        @Value("\${redis.host}")
        private val host: String,
        @Value("\${redis.port}")
        private val port: Int,
        @Value("\${redis.password}")
        private val password: String
) {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val redisStandaloneConfiguration: RedisStandaloneConfiguration = RedisStandaloneConfiguration(host, port);
        redisStandaloneConfiguration.password = RedisPassword.of(password);
        return LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, String> {
        val redisTemplate = RedisTemplate<String, String>();
        // Key와 Value 모두 String으로 직렬화
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()

        // Hash key와 Hash value도 String으로 직렬화
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = StringRedisSerializer()
        redisTemplate.connectionFactory = redisConnectionFactory();
        return redisTemplate
    }
}