package suhyang.inkspire.presentation.common

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import suhyang.inkspire.common.exception.NotFoundException
import suhyang.inkspire.presentation.common.ErrorResponse

@RestControllerAdvice
@Slf4j
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handelNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse: ErrorResponse = ErrorResponse(e.message!!);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}