package com.example.kiosk.entity;

enum class OrderState (
    val key: String,
    val body: String
) {
    PENDING("PENDING", "대기중"),
    COMPLETED("PENDING", "대기중"),
    CANCELED("PENDING", "대기중");
}
