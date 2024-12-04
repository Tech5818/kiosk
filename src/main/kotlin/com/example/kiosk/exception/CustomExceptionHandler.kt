package com.example.kiosk.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler
    fun handleException(exception: CustomException): ResponseEntity<ErrorResponseBody> {
        return ErrorResponseEntity(exception).toResponseEntity()
    }
}