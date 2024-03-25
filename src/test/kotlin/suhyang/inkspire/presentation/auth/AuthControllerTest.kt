package suhyang.inkspire.presentation.auth

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.doThrow
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import suhyang.inkspire.application.auth.OAuthUri
import suhyang.inkspire.common.AbstractRestDocs
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.GITHUB_PROVIDER
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.GOOGLE_PROVIDER
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.OAuth_로그인_링크
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.REDIRECT_URI
import suhyang.inkspire.infrastructure.auth.exception.ProviderNotFoundException


class AuthControllerTest(): AbstractRestDocs() {

    @MockBean
    private lateinit var oAuthUri: OAuthUri;
    @Test
    @DisplayName("성공")
    @Throws(Exception::class)
    fun URI_생성_성공(): Unit {
        given(oAuthUri.generate(GOOGLE_PROVIDER, REDIRECT_URI))
                .willReturn(OAuth_로그인_링크);

        mockMvc.perform(get("/api/auth/{provider}/uri?redirectUri={redirectUri}",
                GOOGLE_PROVIDER, REDIRECT_URI))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("provider")
                                        .description("OAuth 로그인 제공자")
                        ),
                        queryParameters(
                                parameterWithName("redirectUri")
                                        .description("OAuth redirect_uri")
                        )
                ))
    }

    @Test
    @DisplayName("실패: 존재하지 않는 OAuth 제공자")
    @Throws(Exception::class)
    fun URI_생성_실패() {
        doThrow(ProviderNotFoundException())
                .`when`(oAuthUri)
                .generate("WRONG_PROVIDER", REDIRECT_URI)
        mockMvc.perform(get("/api/auth/{provider}/uri?redirectUri={redirectUri}",
                "WRONG_PROVIDER",
                REDIRECT_URI))
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("provider")
                                        .description("OAuth 로그인 제공자")
                        ),
                        queryParameters(
                                parameterWithName("redirectUri")
                                        .description("OAuth Redirect URI")
                        )
                ))
                .andExpect(status().isNotFound())
    }
}