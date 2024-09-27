package suhyang.inkspire.fixture.category

import suhyang.inkspire.infrastructure.category.dto.CategoryRequest

class CategoryFixturesAndDocs {
    companion object {
        val 카테고리_아이디: Long = 1;
        val 카테고리_이름: String = "category_name";

        fun 카테고리_생성_요청(): CategoryRequest.CreateRequest = CategoryRequest.CreateRequest(카테고리_이름);

        fun 카테고리_수정_요청(): CategoryRequest.UpdateRequest = CategoryRequest.UpdateRequest(카테고리_아이디, 카테고리_이름);
    }
}