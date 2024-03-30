package suhyang.inkspire.infrastructure.auth.annotation

import org.springframework.stereotype.Component
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Component
annotation class OAuthProvider(val value: OAuthProviderType)