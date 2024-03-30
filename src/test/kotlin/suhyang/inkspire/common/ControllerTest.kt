package suhyang.inkspire.common

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import suhyang.inkspire.application.auth.AuthService
import suhyang.inkspire.application.auth.OAuthClient
import suhyang.inkspire.application.auth.OAuthUri
import suhyang.inkspire.domain.user.UserRepository
import suhyang.inkspire.presentation.auth.AuthController


@WebMvcTest(AuthController::class)
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
    protected lateinit var userRepository: UserRepository;

    @Throws(JsonProcessingException::class)
    protected open fun toJson(`object`: Any?): String {
        return objectMapper.writeValueAsString(`object`)
    }
}