package com.example.kiosk.entity.dto

data class CreateOrderMenuDTO(
    val id: Long?,

    val orderId: Long?,

    val menuId: Long,

    val quantity: Int,
) {

}
