package com.example.kiosk.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleValidateException(exception: HttpMessageNotReadableException): ResponseEntity<ErrorResponseBody> {
        return ErrorResponseEntity(CustomException(ErrorCode.BAD_REQUEST, "유효성 검사 실패")).toResponseEntity()
    }

    @ExceptionHandler(CustomException::class)
    fun handleException(exception: CustomException, request: HttpServletRequest): ResponseEntity<ErrorResponseBody> {
        return ErrorResponseEntity(exception).toResponseEntity()
    }

}