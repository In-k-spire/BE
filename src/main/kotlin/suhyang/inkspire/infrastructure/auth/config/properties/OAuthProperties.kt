package suhyang.inkspire.infrastructure.auth.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "oauth")
data class OAuthProperties(
        val github: GithubProperties,
        val google: GoogleProperties
) {
    data class GithubProperties(
            val endPoint: String,
            val clientId: String,
            val clientSecret: String
    )

    data class GoogleProperties(
            val endPoint: String,
            val clientId: String,
            val scopes: List<String>,
            val accessType: String
    )
}