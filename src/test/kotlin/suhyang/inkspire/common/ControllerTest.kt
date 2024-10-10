package suhyang.inkspire.common

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import suhyang.inkspire.application.auth.AuthService
import suhyang.inkspire.application.auth.OAuthClient
import suhyang.inkspire.application.auth.OAuthUri
import suhyang.inkspire.application.category.CategoryService
import suhyang.inkspire.domain.user.User
import suhyang.inkspire.domain.user.UserRepository
import suhyang.inkspire.presentation.auth.AuthController
import suhyang.inkspire.presentation.category.CategoryController
import suhyang.inkspire.presentation.common.cookie.JwtTokenCookieGenerator


@WebMvcTest(AuthController::class, CategoryController::class)
abstract class ControllerTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc;

    @Autowired
    protected lateinit var objectMapper: ObjectMapper;

    @MockBean
    protected lateinit var oAuthUri: OAuthUri;

    @MockBean
    protected lateinit var oAuthClient: OAuthClient;

    @MockBean
    protected lateinit var authService: AuthService;

    @MockBean
    protected lateinit var jwtTokenCookieGenerator: JwtTokenCookieGenerator;

    @MockBean
    protected lateinit var categoryService: CategoryService;

    @MockBean
    protected lateinit var userRepository: UserRepository;

    protected val AUTHORIZATION_HEADER_NAME = "Authorization";
    protected val AUTHORIZATION_HEADER_VALUE = "Bearer testtesttest.testtesttest.testtesttest";
    protected val USER_ID = "1";
    protected val USER_NAME = "name"

    protected fun 로그인_유저():User = User(USER_ID, USER_NAME);

    @Throws(JsonProcessingException::class)
    protected open fun toJson(`object`: Any?): String {
        return objectMapper.writeValueAsString(`object`)
    }
}