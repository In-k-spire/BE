package suhyang.inkspire.presentation.auth

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.doThrow
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import suhyang.inkspire.application.auth.OAuthUri
import suhyang.inkspire.common.AbstractRestDocs
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.COOKIE_NAME
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.GITHUB_PROVIDER
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.GOOGLE_PROVIDER
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.JWT_토큰_요청
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.JWT_토큰_응답
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.MAX_AGE
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.OAUTH_로그인_링크
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.OAUTH_인증_코드
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.OAuth_유저
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.REDIRECT_URI
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.RESPONSE_COOKIE_응답
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.리프레쉬_토큰
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.엑세스_토큰
import suhyang.inkspire.fixture.auth.AuthFixturesAndDocs.Companion.엑세스_토큰_재발급_요청
import suhyang.inkspire.infrastructure.auth.dto.OAuthUser
import suhyang.inkspire.infrastructure.auth.exception.InvalidTokenException
import suhyang.inkspire.infrastructure.auth.exception.OAuthException
import suhyang.inkspire.infrastructure.auth.exception.ProviderNotFoundException


class AuthControllerTest(): AbstractRestDocs() {

    @Nested
    @DisplayName("OAuth URI를 생성하고 반환")
    inner class OAuthUriTest {
        @Test
        @DisplayName("성공")
        @Throws(Exception::class)
        fun URI_생성_성공(): Unit {
            given(oAuthUri.generate(GOOGLE_PROVIDER, REDIRECT_URI))
                    .willReturn(OAUTH_로그인_링크);

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
                            ),
                            responseFields(
                                    fieldWithPath("uri")
                                            .type(JsonFieldType.STRING)
                                            .description("OAuth 요청 URI")
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

    @Nested
    @DisplayName("OAuth 코드를 입력받아 JWT 토큰을 반환한다.")
    inner class TokenGenerationTest {

        @Test
        @DisplayName("성공")
        fun 토큰_생성_성공(): Unit {
            given(oAuthClient.get(GOOGLE_PROVIDER, OAUTH_인증_코드, REDIRECT_URI))
                    .willReturn(OAuth_유저());

            given(authService.generateJwtToken(OAuth_유저()))
                    .willReturn(JWT_토큰_응답());

            given(jwtTokenCookieGenerator.generateAccessTokenCookie(엑세스_토큰))
                    .willReturn(RESPONSE_COOKIE_응답());

            given(jwtTokenCookieGenerator.generateRefreshTokenCookie(리프레쉬_토큰))
                    .willReturn(RESPONSE_COOKIE_응답());

            mockMvc.perform(post("/api/auth/{provider}/token", GOOGLE_PROVIDER)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(JWT_토큰_요청()))
            )
                    .andExpect(status().isOk())
                    .andDo(restDocs.document(
                            pathParameters(
                                    parameterWithName("provider")
                                            .description("OAuth 로그인 제공자")
                            ),
                            requestFields(
                                    fieldWithPath("authorizationCode")
                                            .type(JsonFieldType.STRING)
                                            .description("OAuth 인증 코드"),
                                    fieldWithPath("redirectUri")
                                            .type(JsonFieldType.STRING)
                                            .description("OAuth Redirect URI")
                            )
                    ))
        }

        @Test
        @DisplayName("실패")
        fun 토큰_생성_실패(): Unit {
            given(oAuthClient.get(GOOGLE_PROVIDER, OAUTH_인증_코드, REDIRECT_URI))
                    .willReturn(OAuth_유저());

            doThrow(OAuthException())
                    .`when`(authService)
                    .generateJwtToken(OAuth_유저());

            mockMvc.perform(post("/api/auth/{provider}/token", GOOGLE_PROVIDER)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(JWT_토큰_요청()))
            )
                    .andExpect(status().isInternalServerError())
                    .andDo(restDocs.document(
                            pathParameters(
                                    parameterWithName("provider")
                                            .description("OAuth 로그인 제공자")
                            ),
                            requestFields(
                                    fieldWithPath("authorizationCode")
                                            .type(JsonFieldType.STRING)
                                            .description("OAuth 인증 코드"),
                                    fieldWithPath("redirectUri")
                                            .type(JsonFieldType.STRING)
                                            .description("OAuth Redirect URI")
                            )
                    ))
        }

    }

    @Nested
    @DisplayName("refreshToken으로 accessToken을 재발급한다.")
    inner class ReissueTokenTest {

        @Test
        @DisplayName("성공")
        fun 엑세스_토큰_재발급_성공() {
            given(authService.reIssueAccessToken(리프레쉬_토큰))
                    .willReturn(엑세스_토큰);

            mockMvc.perform(patch("/api/auth/reissue/token")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(엑세스_토큰_재발급_요청()))
            )
                    .andExpect(status().isOk())
                    .andDo(restDocs.document(
                            requestFields(
                                    fieldWithPath("refreshToken")
                                            .type(JsonFieldType.STRING)
                                            .description("리프레쉬 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("accessToken")
                                            .type(JsonFieldType.STRING)
                                            .description("재발급된 엑세스 토큰")
                            )
                    ))
        }

        @Test
        @DisplayName("실패")
        fun 엑세스_토큰_재발급_실패() {
           doThrow(InvalidTokenException())
                   .`when`(authService)
                   .reIssueAccessToken(리프레쉬_토큰)

            mockMvc.perform(patch("/api/auth/reissue/token")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(엑세스_토큰_재발급_요청()))
            )
                    .andExpect(status().isUnauthorized())
                    .andDo(restDocs.document(
                            requestFields(
                                    fieldWithPath("refreshToken")
                                            .type(JsonFieldType.STRING)
                                            .description("리프레쉬 토큰")
                            )
                    ))
        }
    }

}