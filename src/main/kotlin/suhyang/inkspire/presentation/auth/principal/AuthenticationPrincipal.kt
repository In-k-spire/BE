package suhyang.inkspire.presentation.auth.principal

import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthenticationPrincipal {
}