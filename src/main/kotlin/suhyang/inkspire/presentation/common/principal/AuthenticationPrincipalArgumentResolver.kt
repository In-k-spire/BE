package suhyang.inkspire.presentation.common.principal

import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import suhyang.inkspire.application.auth.AuthService
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.domain.user.UserRepository
import java.util.*

@RequiredArgsConstructor
@Component
class AuthenticationPrincipalArgumentResolver(
        private val authService: AuthService,
        private val userRepository: UserRepository
): HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal::class.java);
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): User {
        val request: HttpServletRequest = webRequest.getNativeRequest(HttpServletRequest::class.java)!!;
        val accessToken: String = BearerExtractor.extract(request);
        val id: String = authService.extractId(accessToken);
        println(id)
        return userRepository.getUser(id);
    }
}