package com.example.kiosk.entity.dto

import com.example.kiosk.entity.OrderMenu

data class PaymentDTO(
    val total: Int,

    val orderMenus: List<OrderMenu>
)
