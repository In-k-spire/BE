package suhyang.inkspire.common.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import suhyang.inkspire.common.config.properties.OAuthProperties

@Configuration
@EnableConfigurationProperties(
    OAuthProperties::class
)
class PropertiesConfig {
}