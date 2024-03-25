package suhyang.inkspire.infrastructure.auth.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import suhyang.inkspire.infrastructure.auth.config.properties.OAuthProperties

@Configuration
@EnableConfigurationProperties(
    OAuthProperties::class
)
class PropertiesConfig {
}