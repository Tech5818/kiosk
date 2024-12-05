package com.example.kiosk.entity.dto

import com.example.kiosk.entity.Kiosk
import com.example.kiosk.entity.Order
import com.example.kiosk.entity.OrderMenu
import com.example.kiosk.entity.OrderState

data class OrderDTO(
    val id: Long?,

    val table: Kiosk,

    val status: OrderState,

    val orderMenus: MutableList<OrderMenu>,
    ) {
    fun toEntity(): Order = Order(id, status, table, orderMenus)
}
