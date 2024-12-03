package com.example.kiosk.entity

enum class MenuType (
    val key: String,
    val body: String
) {
    NORMAL("NORMAL", "일반 메뉴"),
    NEW("NEW", "신메뉴"),
    SEASON("SEASON", "시즌 메뉴"),
    TIME("TIME", "시간 메뉴")
}