package com.example.kiosk.exception

class CustomException(val errorCode: ErrorCode, val detail: String = ""): RuntimeException()