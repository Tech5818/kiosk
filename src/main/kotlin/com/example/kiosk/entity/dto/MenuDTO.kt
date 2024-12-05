package com.example.kiosk.entity.dto

import com.example.kiosk.entity.Menu
import com.example.kiosk.entity.MenuCategory
import com.example.kiosk.entity.MenuType
import org.jetbrains.annotations.NotNull

data class MenuDTO(
    val id: Long?,

    @NotNull
    val name: String,

    @NotNull
    val imgPath: String,

    @NotNull
    val price: Long,

    @NotNull
    val type: MenuType,

    val discount: Int = 0,

    val calories: Double = 0.0,

    @NotNull
    val category: MenuCategory
) {
    fun toEntity() : Menu = Menu(
        id,
        name,
        imgPath,
        price,
        type,
        discount,
        calories,
        category
        )
}
