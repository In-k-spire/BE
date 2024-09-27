package suhyang.inkspire.domain.review

interface ReviewRepository {

    fun save(review: Review): Unit;

}