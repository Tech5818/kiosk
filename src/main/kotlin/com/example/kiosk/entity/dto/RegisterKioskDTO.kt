package com.example.kiosk.entity.dto

import com.example.kiosk.entity.Kiosk
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull
import java.util.UUID

data class RegisterKioskDTO(
    @NotNull
    @Size(max = 36)
    val id: UUID,

    @NotNull
    @Size(max = 2)
    val number: Int
) {
    fun toEntity() = Kiosk(this.id, this.number)
}
