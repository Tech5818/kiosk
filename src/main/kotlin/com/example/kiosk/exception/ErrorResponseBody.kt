package com.example.kiosk.exception

data class ErrorResponseBody(
    val status: Int,
    val message: String,
    val detail: String
)