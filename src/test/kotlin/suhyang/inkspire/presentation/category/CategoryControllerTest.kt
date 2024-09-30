package suhyang.inkspire.presentation.category

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import suhyang.inkspire.common.AbstractRestDocs
import suhyang.inkspire.fixture.category.CategoryFixturesAndDocs.Companion.카테고리_생성_요청
import suhyang.inkspire.fixture.category.CategoryFixturesAndDocs.Companion.카테고리_수정_요청
import suhyang.inkspire.infrastructure.category.exception.CategoryNotFoundException

class CategoryControllerTest(): AbstractRestDocs() {

    @Nested
    @DisplayName("카테고리를 생성한다.")
    inner class CreateCategory {

        @Test
        @DisplayName("생성 성공")
        fun 카테고리_생성_성공() {
            willDoNothing().given(categoryService).create(USER_ID, 카테고리_생성_요청());

            mockMvc.perform(post("/api/category")
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(카테고리_생성_요청())))
                    .andDo(restDocs.document(
                            requestHeaders(
                                    headerWithName("Authorization")
                                            .description("JWT Access token")
                            ),
                            requestFields(
                                    fieldWithPath("name")
                                            .type(JsonFieldType.STRING)
                                            .description("카테고리 이름")
                            )
                    ))
                    .andExpect(status().isOk())
        }

        @Test
        @DisplayName("생성 실패: 헤더에 값이 없음")
        fun 카테고리_생성_실패() {
            willDoNothing().given(categoryService).create(USER_ID, 카테고리_생성_요청());

            mockMvc.perform(post("/api/category")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(카테고리_생성_요청())))
                    .andDo(restDocs.document(
                            requestFields(
                                    fieldWithPath("name")
                                            .type(JsonFieldType.STRING)
                                            .description("카테고리 이름")
                            )
                    ))
                    .andExpect(status().isUnauthorized())
        }
    }

    @Nested
    @DisplayName("카테고리 이름을 수정한다.")
    inner class UpdateCategory {
        @Test
        @DisplayName("성공")
        fun 카테고리_수정_성공() {
            willDoNothing().given(categoryService).update(USER_ID, 카테고리_수정_요청());

            mockMvc.perform(patch("/api/category")
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(카테고리_수정_요청())))
                    .andDo(restDocs.document(
                            requestHeaders(
                                    headerWithName("Authorization")
                                            .description("JWT Access token")
                            ),
                            requestFields(
                                    fieldWithPath("id")
                                            .type(JsonFieldType.NUMBER)
                                            .description("수정할 카테고리 아이디"),
                                    fieldWithPath("name")
                                            .type(JsonFieldType.STRING)
                                            .description("수정할 카테고리 이름")
                            )
                    ))
                    .andExpect(status().isOk())
        }

        @Test
        @DisplayName("실패: 존재하지 않는 카테고리")
        fun 카테고리_수정_실패() {
            doThrow(CategoryNotFoundException())
                    .`when`(categoryService)
                    .update(USER_ID, 카테고리_수정_요청())

            mockMvc.perform(patch("/api/category")
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(카테고리_수정_요청())))
                    .andDo(restDocs.document(
                            requestHeaders(
                                    headerWithName("Authorization")
                                            .description("JWT Access token")
                            ),
                            requestFields(
                                    fieldWithPath("id")
                                            .type(JsonFieldType.NUMBER)
                                            .description("존재하지 않는 카테고리 아이디"),
                                    fieldWithPath("name")
                                            .type(JsonFieldType.STRING)
                                            .description("수정할 카테고리 이름")
                            )
                    ))
                    .andExpect(status().isNotFound())
        }
    }

}