package com.example.kiosk.entity;

enum class OrderState (
    val key: String,
    val body: String
) {
    PENDING("PENDING", "주문"),
    COMPLETED("COMPLETED", "완료"),
    CANCELED("CANCELED", "취소");
}
