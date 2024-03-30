package suhyang.inkspire.presentation.common

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import suhyang.inkspire.common.exception.InternalException
import suhyang.inkspire.common.exception.NotFoundException
import suhyang.inkspire.common.exception.UnauthorizedException
import suhyang.inkspire.presentation.common.ErrorResponse

@RestControllerAdvice
@Slf4j
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handelNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse: ErrorResponse = ErrorResponse(e.message!!);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InternalException::class)
    fun handelInternalException(e: InternalException): ResponseEntity<ErrorResponse> {
        val errorResponse: ErrorResponse = ErrorResponse(e.message!!);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handelUnauthorizedException(e: UnauthorizedException): ResponseEntity<ErrorResponse> {
        val errorResponse: ErrorResponse = ErrorResponse(e.message!!);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}