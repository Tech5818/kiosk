package com.example.kiosk.entity.dto

import com.example.kiosk.entity.MenuCategory
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class CategoryDTO(
    val id: Long?,

    @NotNull
    @Size(max = 10)
    val name: String,

    val isSpecial: Boolean = false
) {
    fun toEntity(): MenuCategory = MenuCategory(id, name, isSpecial)
}
