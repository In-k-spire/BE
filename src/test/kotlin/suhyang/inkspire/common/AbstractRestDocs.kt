package suhyang.inkspire.common

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultHandler
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import suhyang.inkspire.config.RestDocsConfig
import suhyang.inkspire.presentation.auth.AuthController
import suhyang.inkspire.presentation.auth.AuthControllerTest


@Disabled
@WebMvcTest(AuthController::class)
@Import(RestDocsConfig::class)
@ExtendWith(RestDocumentationExtension::class)
abstract class AbstractRestDocs {

    @Autowired
    protected lateinit var restDocs: RestDocumentationResultHandler;

    @Autowired
    protected lateinit var mockMvc: MockMvc;

    @BeforeEach
    fun setUp(context: WebApplicationContext?,
              provider: RestDocumentationContextProvider?) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context!!)
                .apply<DefaultMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(provider))
                .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
                .alwaysDo<DefaultMockMvcBuilder>(restDocs)
                .addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
                .build()
    }
}