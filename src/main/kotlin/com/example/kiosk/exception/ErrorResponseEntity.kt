package com.example.kiosk.exception

import org.springframework.http.ResponseEntity

data class ErrorResponseEntity(val exception: CustomException) {
    private val errorCode: ErrorCode = exception.errorCode

    fun toResponseEntity(): ResponseEntity<ErrorResponseBody> {
        return ResponseEntity(
            ErrorResponseBody(this.errorCode.httpStatus.value(), this.errorCode.message, this.exception.detail),
            this.errorCode.httpStatus
        )
    }
}
